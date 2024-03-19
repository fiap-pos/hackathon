package br.com.fiap.hackathon.ponto.adapters.gateways.producao.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FilaProducaoRequest (Long codigo, List<FilaProducaoItem> itens) {

}
