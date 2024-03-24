package br.com.fiap.hackathon.ponto.adapters.repository;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.adapters.repository.jpa.PontoJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.FilaRelatorioPontoMapper;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.repository.sqs.RelatorioPontoSqsPublisher;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import br.com.fiap.hackathon.ponto.core.exceptions.UnexpectedDomainException;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaStatusDiaOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.EnviaRelatorioFilaRelatoriosOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.RegistraPontoOutputPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.util.Objects.isNull;

@Repository
@RequiredArgsConstructor
public class PontoRepository implements RegistraPontoOutputPort, BuscaStatusDiaOutputPort, EnviaRelatorioFilaRelatoriosOutputPort {

    private final Logger logger = LoggerFactory.getLogger(PontoRepository.class);
    private final PontoJpaRepository jpaRepository;
    private final PontoMapper mapper;
    private final FilaRelatorioPontoMapper filaRelatorioPontoMapper;
    private final RelatorioPontoSqsPublisher relatorioPontoSqsPublisher;

    @Override
    public PontoDTO registrar(PontoDTO pontoIn) {
        var ponto = mapper.toPonto(pontoIn);
        var pontoDoDia = buscaStatusDiaPorMatricula(ponto.getMatricula());
        var ultimoStatusDoDia = getLastElement(pontoDoDia);

        if (pontoDoDia.isEmpty() || isNull(ultimoStatusDoDia) || ultimoStatusDoDia.tipoRegistro().equals(TipoRegistroEnum.SAIDA)) {
            ponto.setTipoRegistro(TipoRegistroEnum.ENTRADA);
        } else {
            ponto.setTipoRegistro(TipoRegistroEnum.SAIDA);
        }

        var pontoSalvo = jpaRepository.save(ponto);
        return mapper.toPontoDTO(pontoSalvo);
    }

    @Override
    public List<PontoDTO> buscaStatusDiaPorMatricula(String matricula) {
        var ponto = jpaRepository.findByMatriculaAndRegistroBetween(matricula, LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        return mapper.toPontoDTOList(ponto);
    }

    private PontoDTO getLastElement(List<PontoDTO> pontoDoDia) {
        return (!pontoDoDia.isEmpty()) ? pontoDoDia.get(pontoDoDia.size() - 1) : null;
    }

    @Override
    public String enviarRelatorio(RelatorioPontoDTO relatorioPontoDTO) {
        try {
            FilaRelatorioDTO filaRelatorioPontoDTO = filaRelatorioPontoMapper.toFilaRelatorioPontoDTO(relatorioPontoDTO);
            relatorioPontoSqsPublisher.publicaFilaRelatorios(filaRelatorioPontoDTO);
            return "Relatório Matricula: " + relatorioPontoDTO.matricula() +
                    " - Mês/Ano: " + String.format("%02d", relatorioPontoDTO.mes()) + "/" + relatorioPontoDTO.ano() +
                    " enviado para fila de relatórios com sucesso!";
        } catch (Exception e) {
            logger.error("Erro ao enviar relatório para fila de relatórios", e);
            throw new UnexpectedDomainException("Erro ao enviar relatório para fila de relatórios");
        }
    }
}
