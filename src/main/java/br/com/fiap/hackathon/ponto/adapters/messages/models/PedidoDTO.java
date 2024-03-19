package br.com.fiap.hackathon.ponto.adapters.messages.models;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        String id,
        Long codigo,
        List<ItemPedidoDTO> itens,
        StatusPedidoEnum status,
        LocalDateTime dataCriacao
) {
    public PedidoDTO(Long codigo, List<ItemPedidoDTO> itens, StatusPedidoEnum status, LocalDateTime dataCriacao) {
        this(null, codigo, itens, status, dataCriacao);
    }
}
