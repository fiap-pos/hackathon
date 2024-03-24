package br.com.fiap.hackathon.ponto.core.ports.in;

import br.com.fiap.hackathon.ponto.core.dtos.UsuarioDTO;

public interface BuscaUsuarioInputPort {
    UsuarioDTO buscaUsuarioPorMatricula(String matricula);
}
