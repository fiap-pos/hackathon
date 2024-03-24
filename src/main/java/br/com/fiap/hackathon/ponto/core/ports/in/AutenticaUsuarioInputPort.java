package br.com.fiap.hackathon.ponto.core.ports.in;

import br.com.fiap.hackathon.ponto.core.dtos.AutenticaUsuarioDTO;
import br.com.fiap.hackathon.ponto.core.dtos.AuthTokenDTO;

public interface AutenticaUsuarioInputPort {
    AuthTokenDTO autenticaUsuario(AutenticaUsuarioDTO usuarioDTO);
}
