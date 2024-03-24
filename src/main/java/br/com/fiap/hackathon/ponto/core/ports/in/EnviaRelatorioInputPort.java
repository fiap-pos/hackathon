package br.com.fiap.hackathon.ponto.core.ports.in;

import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;

public interface EnviaRelatorioInputPort {

    String enviaRelatorioPontoParaFilaRelatorios(RelatorioPontoDTO relatorioPontoDTO);
}
