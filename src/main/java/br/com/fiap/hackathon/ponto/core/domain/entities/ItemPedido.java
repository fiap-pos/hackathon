package br.com.fiap.hackathon.ponto.core.domain.entities;

import java.math.BigDecimal;

public class ItemPedido {
    private Long produtoId;
    private String produtoNome;
    private String produtoDescricao;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    public ItemPedido(Long produtoId, String produtoNome, String produtoDescricao, BigDecimal valorUnitario, Integer quantidade) {
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.produtoDescricao = produtoDescricao;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return BigDecimal.valueOf(quantidade).multiply(valorUnitario);
    }
}
