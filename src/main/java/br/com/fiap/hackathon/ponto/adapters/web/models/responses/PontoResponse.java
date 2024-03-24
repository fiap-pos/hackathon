package br.com.fiap.hackathon.ponto.adapters.web.models.responses;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class PontoResponse {

    private Long id;
    private String matricula;
    private LocalDateTime registro;
    private TipoRegistroEnum tipoRegistro;

    public PontoResponse(Long id, String matricula, LocalDateTime registro, TipoRegistroEnum tipoRegistro) {
        this.id = id;
        this.matricula = matricula;
        this.registro = registro;
        this.tipoRegistro = tipoRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDateTime getRegistro() {
        return registro;
    }

    public void setRegistro(LocalDateTime registro) {
        this.registro = registro;
    }

    public TipoRegistroEnum getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(TipoRegistroEnum tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
}
