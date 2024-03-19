package br.com.fiap.hackathon.ponto.core.ports.out;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;

public interface RegistraPontoOutputPort {
    PontoDTO registrar(PontoDTO pontoIn);
}
