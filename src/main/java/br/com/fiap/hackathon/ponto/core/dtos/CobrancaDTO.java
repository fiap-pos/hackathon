package br.com.fiap.hackathon.ponto.core.dtos;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;

import java.math.BigDecimal;

public record CobrancaDTO(Long id, Long pedidoId, BigDecimal valor, StatusPedidoEnum status, String qrCode) {
}
