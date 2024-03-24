package br.com.fiap.hackathon.ponto.adapters.repository;

import br.com.fiap.hackathon.ponto.adapters.repository.jpa.UsuarioJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.UsuarioMapper;
import br.com.fiap.hackathon.ponto.core.domain.exceptions.EntityNotFoundException;
import br.com.fiap.hackathon.ponto.core.dtos.UsuarioDTO;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaUsuarioPorMatriculaOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsuarioRepository implements BuscaUsuarioPorMatriculaOutputPort {


    private final UsuarioJpaRepository usuarioJpaRepository;

    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO buscaPorMatricula(String matricula) {
        var usuario = usuarioJpaRepository.findById(matricula).orElseThrow(
                () -> new EntityNotFoundException("Usuário com matricula "+ matricula +" não encontrado."));
        return usuarioMapper.toDTO(usuario);
    }

}
