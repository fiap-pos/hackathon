package br.com.fiap.hackathon.ponto.core.ports.out.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
public interface EditaProdutoOutputPort {

    ProdutoDTO editar(ProdutoDTO produto, Long id);
}
