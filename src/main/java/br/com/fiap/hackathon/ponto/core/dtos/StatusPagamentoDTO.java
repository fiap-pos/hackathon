package br.com.fiap.hackathon.ponto.core.dtos;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusCobrancaEnum;

public record StatusPagamentoDTO(
        Long id,
        String statusPedido,
        StatusCobrancaEnum statusPagamento
) {
}
