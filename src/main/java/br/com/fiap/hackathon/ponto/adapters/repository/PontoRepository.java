package br.com.fiap.hackathon.ponto.adapters.repository;

import br.com.fiap.hackathon.ponto.adapters.repository.jpa.PontoJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaStatusDiaOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.RegistraPontoOutputPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.util.Objects.isNull;

@Repository
public class PontoRepository implements RegistraPontoOutputPort, BuscaStatusDiaOutputPort {

    private final PontoJpaRepository jpaRepository;
    private final PontoMapper mapper;

    public PontoRepository(PontoJpaRepository jpaRepository, PontoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

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
    public List<PontoDTO> buscaStatusDia() {
        var ponto = jpaRepository.find(LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        return mapper.toPontoDTOList(ponto);
    }

    @Override
    public List<PontoDTO> buscaStatusDiaPorMatricula(String matricula) {
        var ponto = jpaRepository.findByMatriculaAndRegistroBetween(matricula, LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        return mapper.toPontoDTOList(ponto);
    }

    private PontoDTO getLastElement(List<PontoDTO> pontoDoDia) {
        return (!pontoDoDia.isEmpty()) ? pontoDoDia.get(pontoDoDia.size() - 1) : null;
    }
}
