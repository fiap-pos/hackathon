package br.com.fiap.hackathon.ponto.core.ports.out;

public interface GeraRelatorioOutputPort {

    String geraRelatorio(String matricula, int mes, int ano);
}
