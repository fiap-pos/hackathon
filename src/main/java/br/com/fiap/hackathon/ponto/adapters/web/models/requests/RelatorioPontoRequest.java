package br.com.fiap.hackathon.ponto.adapters.web.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelatorioPontoRequest {
    private String matricula;
    private int mes;
    private int ano;
}
