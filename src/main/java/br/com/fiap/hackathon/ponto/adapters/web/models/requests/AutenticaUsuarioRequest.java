package br.com.fiap.hackathon.ponto.adapters.web.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AutenticaUsuarioRequest {
    private String matricula;
    private String senha;
}
