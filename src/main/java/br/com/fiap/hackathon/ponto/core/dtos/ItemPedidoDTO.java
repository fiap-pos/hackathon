package br.com.fiap.hackathon.ponto.core.dtos;

import br.com.fiap.hackathon.ponto.core.domain.entities.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        Long produtoId,
        String produtoNome,
        String produtoDescricao,
        BigDecimal valorUnitario,
        Integer quantidade
) {

    public ItemPedidoDTO(ItemPedido itemPedido) {
        this(itemPedido.getProdutoId(), itemPedido.getProdutoNome(), itemPedido.getProdutoDescricao(), itemPedido.getValorUnitario(), itemPedido.getQuantidade());
    }

    public BigDecimal getValorTotal() {
        return BigDecimal.valueOf(quantidade).multiply(valorUnitario);
    }
}
