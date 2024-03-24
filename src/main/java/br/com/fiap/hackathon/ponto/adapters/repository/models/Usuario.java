package br.com.fiap.hackathon.ponto.adapters.repository.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @Column(name = "matricula")
    private String matricula;

    @Column(name = "senha")
    private String senha;

    @Column(name = "email")
    private String email;
}
