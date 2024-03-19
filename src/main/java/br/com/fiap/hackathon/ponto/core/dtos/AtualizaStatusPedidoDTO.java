package br.com.fiap.hackathon.ponto.core.dtos;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;

public record AtualizaStatusPedidoDTO(StatusPedidoEnum status) {
}
