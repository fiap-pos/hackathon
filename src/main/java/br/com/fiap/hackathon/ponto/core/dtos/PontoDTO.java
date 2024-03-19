package br.com.fiap.hackathon.ponto.core.dtos;

import br.com.fiap.hackathon.ponto.core.domain.entities.Ponto;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;

import java.time.LocalDateTime;

public record PontoDTO (Long id, String matricula, LocalDateTime horaRegistro, TipoRegistroEnum tipoRegistro) {

    public PontoDTO(String matricula, LocalDateTime horaRegistro, TipoRegistroEnum tipoRegistro) {
        this(null, matricula, horaRegistro, tipoRegistro);
    }

    public PontoDTO(Long id) {
        this(id, null, null, null);
    }

    public PontoDTO(Ponto ponto) {
        this(ponto.getId(), ponto.getMatricula(), ponto.getRegistro(), ponto.getTipoRegistro());
    }
}
