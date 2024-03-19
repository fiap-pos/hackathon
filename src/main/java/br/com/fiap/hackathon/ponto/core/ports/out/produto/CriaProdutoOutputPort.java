package br.com.fiap.hackathon.ponto.core.ports.out.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
public interface CriaProdutoOutputPort {

    ProdutoDTO criar(ProdutoDTO produto);
}
