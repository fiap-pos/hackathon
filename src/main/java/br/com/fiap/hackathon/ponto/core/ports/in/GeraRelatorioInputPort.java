package br.com.fiap.hackathon.ponto.core.ports.in;

public interface GeraRelatorioInputPort {

    String geraRelatorio(String matricula, int mes, int ano);
}
