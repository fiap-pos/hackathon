package br.com.fiap.hackathon.ponto.core.ports.in;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDiaDTO;

import java.util.List;

public interface BuscaStatusDiaInputPort {
    PontoDiaDTO buscaStatusDiaPorMatricula(String matricula);
}
