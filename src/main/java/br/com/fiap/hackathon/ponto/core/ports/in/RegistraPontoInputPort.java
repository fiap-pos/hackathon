package br.com.fiap.hackathon.ponto.core.ports.in;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;

public interface RegistraPontoInputPort {
    PontoDTO registrar(PontoDTO ponto, String authorization);
}
