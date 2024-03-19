package br.com.fiap.hackathon.ponto.adapters.repository.models;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public class Ponto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    @Column(name = "hora_data_registro")
    private LocalDateTime registro;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_registro")
    private TipoRegistroEnum tipoRegistro;

    public Ponto() {
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
