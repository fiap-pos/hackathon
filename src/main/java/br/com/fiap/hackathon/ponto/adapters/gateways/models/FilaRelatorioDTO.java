package br.com.fiap.hackathon.ponto.adapters.gateways.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FilaRelatorioDTO(String matricula, int mes, int ano, String email) {
}
