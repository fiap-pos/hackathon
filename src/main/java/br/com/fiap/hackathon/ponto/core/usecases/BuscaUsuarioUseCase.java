package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.dtos.UsuarioDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaUsuarioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaUsuarioPorMatriculaOutputPort;
import org.springframework.stereotype.Service;

@Service
public class BuscaUsuarioUseCase implements BuscaUsuarioInputPort {

    private final BuscaUsuarioPorMatriculaOutputPort buscaUsuarioPorMatriculaOutputPort;

    public BuscaUsuarioUseCase(BuscaUsuarioPorMatriculaOutputPort buscaUsuarioOutputPort) {
        this.buscaUsuarioPorMatriculaOutputPort = buscaUsuarioOutputPort;
    }

    @Override
    public UsuarioDTO buscaUsuarioPorMatricula(String matricula) {
        return buscaUsuarioPorMatriculaOutputPort.buscaPorMatricula(matricula);
    }
}
