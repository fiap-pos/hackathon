package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.RegistraPontoInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.RegistraPontoOutputPort;
import org.springframework.stereotype.Service;

@Service
public class RegistraPontoUseCase implements RegistraPontoInputPort {

    private final RegistraPontoOutputPort registraPontoOutputPort;

    public RegistraPontoUseCase(RegistraPontoOutputPort registraPontoOutputPort) {
        this.registraPontoOutputPort = registraPontoOutputPort;
    }

    @Override
    public PontoDTO registrar(PontoDTO pontoIn) {
        return registraPontoOutputPort.registrar(pontoIn);
    }
}
