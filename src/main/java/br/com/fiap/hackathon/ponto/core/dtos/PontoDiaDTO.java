package br.com.fiap.hackathon.ponto.core.dtos;

import java.util.List;

public record PontoDiaDTO(List<PontoDTO> registros, String totalHorasDia) {
}
