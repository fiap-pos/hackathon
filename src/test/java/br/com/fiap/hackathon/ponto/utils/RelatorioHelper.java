package br.com.fiap.hackathon.ponto.utils;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;

public abstract class RelatorioHelper {
    private static final String MATRICULA = "12345";
    private static final int MES = 2;
    private static final int ANO = 2024;
    public static FilaRelatorioDTO getFilaRelatorioDTO(){
        return new FilaRelatorioDTO(MATRICULA, MES, ANO);
    }
    public static RelatorioPontoDTO getRelatorioPontoDTO(){
        return new RelatorioPontoDTO(MATRICULA, MES, ANO);
    }
    public static String getMensagem() {
        return "Relatório Matricula: " + getRelatorioPontoDTO().matricula() +
                " - Mês/Ano: " + String.format("%02d", getRelatorioPontoDTO().mes()) + "/" + getRelatorioPontoDTO().ano() +
                " enviado para fila de relatórios com sucesso!";
    }
}
