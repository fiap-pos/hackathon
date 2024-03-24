package br.com.fiap.hackathon.ponto.utils;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.UsuarioDTO;

public abstract class RelatorioHelper {
    private static final String MATRICULA = "12345";
    private static final int MES = 2;
    private static final int ANO = 2024;
    private static final String SENHA = "12345";

    private static final String EMAIL = "teste@email.com";
    public static FilaRelatorioDTO getFilaRelatorioDTO(){
        return new FilaRelatorioDTO(MATRICULA, MES, ANO, EMAIL);
    }
    public static RelatorioPontoDTO getRelatorioPontoDTO(){
        return new RelatorioPontoDTO(MATRICULA, MES, ANO, EMAIL, null);
    }
    public static String getMensagem() {
        return "Relatório Matricula: " + getRelatorioPontoDTO().matricula() +
                " - Mês/Ano: " + String.format("%02d", getRelatorioPontoDTO().mes()) + "/" + getRelatorioPontoDTO().ano() +
                " enviado para fila de relatórios com sucesso!";
    }

    public static UsuarioDTO getUsuarioDTO(){
        return new UsuarioDTO(MATRICULA, EMAIL, SENHA);
    }
}
