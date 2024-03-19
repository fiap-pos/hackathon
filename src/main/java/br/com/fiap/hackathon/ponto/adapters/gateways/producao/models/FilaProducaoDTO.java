package br.com.fiap.hackathon.ponto.adapters.gateways.producao.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FilaProducaoDTO(Long codigo, List<FilaProducaoItemDTO> itens) {

}
