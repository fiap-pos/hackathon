package br.com.fiap.hackathon.ponto.core.ports.out;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;

import java.util.List;

public interface BuscaStatusDiaOutputPort {
    List<PontoDTO> buscaStatusDia();

    List<PontoDTO> buscaStatusDiaPorMatricula(String matricula);
}
