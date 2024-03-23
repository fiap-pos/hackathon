package br.com.fiap.hackathon.ponto.adapters.web.models.requests;

import lombok.Getter;

@Getter
public class RelatorioPontoRequest {
    private String matricula;
    private int mes;
    private int ano;
}
