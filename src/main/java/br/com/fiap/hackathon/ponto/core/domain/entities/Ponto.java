package br.com.fiap.hackathon.ponto.core.domain.entities;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;

import java.time.LocalDateTime;

public class Ponto {
    private Long id;
    private String matricula;
    private LocalDateTime registro;
    private TipoRegistroEnum tipoRegistro;

    public Ponto(Long id, String matricula, LocalDateTime registro, TipoRegistroEnum tipoRegistro) {
        this.id = id;
        this.matricula = matricula;
        this.registro = registro;
        this.tipoRegistro = tipoRegistro;
    }
    public Ponto(String matricula, LocalDateTime registro, TipoRegistroEnum tipoRegistro) {
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
