package br.com.fiap.hackathon.ponto.adapters.repository;

import br.com.fiap.hackathon.ponto.adapters.repository.jpa.PontoJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.out.RegistraPontoOutputPort;

public class PontoRepository implements RegistraPontoOutputPort {

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
}
