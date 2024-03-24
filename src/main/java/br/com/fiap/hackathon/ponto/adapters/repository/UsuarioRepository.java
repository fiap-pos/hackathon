package br.com.fiap.hackathon.ponto.adapters.repository;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.adapters.repository.jpa.PontoJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.jpa.UsuarioJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.FilaRelatorioPontoMapper;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.UsuarioMapper;
import br.com.fiap.hackathon.ponto.adapters.repository.sqs.RelatorioPontoSqsPublisher;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.UsuarioDTO;
import br.com.fiap.hackathon.ponto.core.exceptions.UnexpectedDomainException;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaUsuarioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaStatusDiaOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaUsuarioOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.EnviaRelatorioFilaRelatoriosOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.RegistraPontoOutputPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.util.Objects.isNull;

@Repository
@RequiredArgsConstructor
public class UsuarioRepository implements BuscaUsuarioOutputPort {

    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioMapper mapper;

    @Override
    public UsuarioDTO buscaUsuarioPorMatricula(String matricula) {
        var usuario = jpaRepository.findByMatricula(matricula);
        return mapper.toUsuarioDTO(usuario);
    }
}
