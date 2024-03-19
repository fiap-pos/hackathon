package br.com.fiap.hackathon.ponto.core.ports.in;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;

import java.util.List;

public interface BuscaStatusDiaInputPort {
    List<PontoDTO> buscaStatusDia();
}
