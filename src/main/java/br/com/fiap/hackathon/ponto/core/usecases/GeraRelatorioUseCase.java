package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.ports.in.GeraRelatorioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.GeraRelatorioOutputPort;
import org.springframework.stereotype.Service;

@Service
public class GeraRelatorioUseCase implements GeraRelatorioInputPort {

    private final GeraRelatorioOutputPort geraRelatorioOutputPort;

    public GeraRelatorioUseCase(GeraRelatorioOutputPort geraRelatorioOutputPort) {
        this.geraRelatorioOutputPort = geraRelatorioOutputPort;
    }

    @Override
    public String geraRelatorio(String matricula, int mes, int ano) {
        return geraRelatorioOutputPort.geraRelatorio(matricula, mes, ano);
    }
}
