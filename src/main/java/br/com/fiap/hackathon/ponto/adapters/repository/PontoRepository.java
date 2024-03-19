package br.com.fiap.hackathon.ponto.adapters.repository;

import br.com.fiap.hackathon.ponto.adapters.repository.jpa.PontoJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaStatusDiaOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.RegistraPontoOutputPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Repository
public class PontoRepository implements RegistraPontoOutputPort, BuscaStatusDiaOutputPort {

    private final PontoJpaRepository jpaRepository;
    private final PontoMapper mapper;

    public PontoRepository(PontoJpaRepository jpaRepository, PontoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PontoDTO registrar(PontoDTO pontoIn) {
        var ponto = mapper.toPonto(pontoIn);
        var pontoSalvo = jpaRepository.save(ponto);
        return mapper.toPontoDTO(pontoSalvo);
    }

    @Override
    public List<PontoDTO> buscaStatusDia() {
        var ponto = jpaRepository.find(LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        return mapper.toPontoDTOList(ponto);
    }
}
