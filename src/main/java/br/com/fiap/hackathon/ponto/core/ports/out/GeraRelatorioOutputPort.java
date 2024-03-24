package br.com.fiap.hackathon.ponto.core.ports.out;

import java.io.IOException;

public interface GeraRelatorioOutputPort {

    String geraRelatorio(String matricula, int mes, int ano);

    void deleteRelatorio(String nomeArquivoRelatorio) throws IOException;
}
