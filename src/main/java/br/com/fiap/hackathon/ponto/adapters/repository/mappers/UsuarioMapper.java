package br.com.fiap.hackathon.ponto.adapters.repository.mappers;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Usuario;
import br.com.fiap.hackathon.ponto.core.dtos.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getMatricula(), usuario.getEmail(), usuario.getSenha());
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.matricula(), usuarioDTO.email(), usuarioDTO.senha());
    }
}
