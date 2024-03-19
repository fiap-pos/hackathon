package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaStatusDiaOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaStatusDiaUseCase implements BuscaStatusDiaInputPort {

    private final BuscaStatusDiaOutputPort buscaStatusDiaOutputPort;

    public BuscaStatusDiaUseCase(BuscaStatusDiaOutputPort buscaStatusDiaOutputPort) {
        this.buscaStatusDiaOutputPort = buscaStatusDiaOutputPort;
    }

    @Override
    public List<PontoDTO> buscaStatusDia() {
        return buscaStatusDiaOutputPort.buscaStatusDia();
    }
}
