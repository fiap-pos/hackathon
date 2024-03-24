package br.com.fiap.hackathon.ponto.core.ports.out;

import br.com.fiap.hackathon.ponto.core.dtos.UsuarioDTO;

public interface BuscaUsuarioPorMatriculaOutputPort {
    UsuarioDTO buscaPorMatricula(String matricula);
}
