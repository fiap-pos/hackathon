package br.com.fiap.hackathon.ponto.adapters.web.models.requests;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class PontoRequest {
    private String matricula;
    private LocalDateTime registro;
    private TipoRegistroEnum tipoRegistro;

    public PontoRequest() {
    }

    public PontoRequest(String matricula, LocalDateTime registro, TipoRegistroEnum tipoRegistro) {
        this.matricula = matricula;
        this.registro = registro;
        this.tipoRegistro = tipoRegistro;
    }

    public PontoDTO toPontoDTO() {
        return new PontoDTO(this.matricula, this.registro, this.tipoRegistro);
    }
}
