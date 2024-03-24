package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.UsuarioDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaUsuarioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaStatusDiaOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaUsuarioOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaUsuarioUseCase implements BuscaUsuarioInputPort {

    private final BuscaUsuarioOutputPort buscaUsuarioOutputPort;

    public BuscaUsuarioUseCase(BuscaUsuarioOutputPort buscaUsuarioOutputPort) {
        this.buscaUsuarioOutputPort = buscaUsuarioOutputPort;
    }
    @Override
    public UsuarioDTO buscaUsuarioPorMatricula(String matricula) {
        return buscaUsuarioOutputPort.buscaUsuarioPorMatricula(matricula);
    }
}
