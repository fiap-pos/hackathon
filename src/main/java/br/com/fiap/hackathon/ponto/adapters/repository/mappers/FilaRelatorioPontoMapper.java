package br.com.fiap.hackathon.ponto.adapters.repository.mappers;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import org.springframework.stereotype.Component;

@Component
public class FilaRelatorioPontoMapper {

    public FilaRelatorioDTO toFilaRelatorioPontoDTO(RelatorioPontoDTO relatorioPontoDTO) {
        return new FilaRelatorioDTO(relatorioPontoDTO.matricula(), relatorioPontoDTO.mes(), relatorioPontoDTO.ano());
    }
}
