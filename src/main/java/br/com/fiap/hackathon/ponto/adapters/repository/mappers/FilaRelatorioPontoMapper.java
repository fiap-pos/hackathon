package br.com.fiap.hackathon.ponto.adapters.repository.mappers;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;

@Component
public class FilaRelatorioPontoMapper {

    public FilaRelatorioDTO toFilaRelatorioPontoDTO(RelatorioPontoDTO relatorioPontoDTO) {
                return new FilaRelatorioDTO(
                relatorioPontoDTO.matricula(),
                relatorioPontoDTO.mes(),
                relatorioPontoDTO.ano(),
                relatorioPontoDTO.email()
        );
    }

    public RelatorioPontoDTO toRelatorioPontoDTO(FilaRelatorioDTO filaRelatorioDTO, String nomeArquivoRelatorio) {
        return new RelatorioPontoDTO(
                filaRelatorioDTO.matricula(),
                filaRelatorioDTO.mes(),
                filaRelatorioDTO.ano(),
                filaRelatorioDTO.email(),
                nomeArquivoRelatorio
        );
    }

}
