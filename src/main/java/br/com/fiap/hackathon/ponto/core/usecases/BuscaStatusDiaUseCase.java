package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDiaDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaStatusDiaOutputPort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BuscaStatusDiaUseCase implements BuscaStatusDiaInputPort {

    private final BuscaStatusDiaOutputPort buscaStatusDiaOutputPort;

    public BuscaStatusDiaUseCase(BuscaStatusDiaOutputPort buscaStatusDiaOutputPort) {
        this.buscaStatusDiaOutputPort = buscaStatusDiaOutputPort;
    }

    @Override
    public PontoDiaDTO buscaStatusDiaPorMatricula(String matricula) {
        var pontosDia = buscaStatusDiaOutputPort.buscaStatusDiaPorMatricula(matricula);
        Duration totalHoras = calcularTotalHoras(pontosDia);
        long horas = totalHoras.toHours();
        long minutos = totalHoras.toMinutesPart();
        var totalHorasDia = String.format("%02d:%02d", horas, minutos);
        return new PontoDiaDTO(pontosDia, totalHorasDia);
    }

    private Duration calcularTotalHoras(List<PontoDTO> pontosDia) {
        Duration totalHoras = Duration.ZERO;
        LocalDateTime ultimaEntrada = null;

        for (PontoDTO pontoDTO : pontosDia) {
            if (pontoDTO.tipoRegistro() == TipoRegistroEnum.ENTRADA) {
                ultimaEntrada = pontoDTO.horaRegistro();
            } else if (pontoDTO.tipoRegistro() == TipoRegistroEnum.SAIDA && ultimaEntrada != null) {
                Duration duracao = Duration.between(ultimaEntrada, pontoDTO.horaRegistro());
                totalHoras = totalHoras.plus(duracao);
                ultimaEntrada = null; // Preparando para a próxima entrada
            }
        }

        return totalHoras;
    }
}
