package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.domain.entities.AuthToken;
import br.com.fiap.hackathon.ponto.core.domain.entities.Usuario;
import br.com.fiap.hackathon.ponto.core.domain.exceptions.EntityNotFoundException;
import br.com.fiap.hackathon.ponto.core.domain.exceptions.UnauthorizedException;
import br.com.fiap.hackathon.ponto.core.dtos.AutenticaUsuarioDTO;
import br.com.fiap.hackathon.ponto.core.dtos.AuthTokenDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.AutenticaUsuarioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaUsuarioPorMatriculaOutputPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticaUsuarioUseCase implements AutenticaUsuarioInputPort {

    private final BuscaUsuarioPorMatriculaOutputPort buscaUsuarioPorMatriculaOutputPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthToken authToken;

    public AutenticaUsuarioUseCase(BuscaUsuarioPorMatriculaOutputPort buscaUsuarioPorMatriculaOutputPort, PasswordEncoder passwordEncoder, AuthToken authToken) {
        this.buscaUsuarioPorMatriculaOutputPort = buscaUsuarioPorMatriculaOutputPort;
        this.authToken = authToken;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthTokenDTO autenticaUsuario(AutenticaUsuarioDTO authDTO) {
        try {
            var usuarioDTO = buscaUsuarioPorMatriculaOutputPort.buscaPorMatricula(authDTO.matricula());
            if(passwordEncoder.matches(authDTO.senha(), usuarioDTO.senha())) {
                var usuario = new Usuario(usuarioDTO.matricula(), usuarioDTO.email(), usuarioDTO.senha());
                return authToken.criar(usuario);
            }
        } catch (EntityNotFoundException ignored) {}

        throw new UnauthorizedException("Usuário ou senha inválidos");
    }
}
