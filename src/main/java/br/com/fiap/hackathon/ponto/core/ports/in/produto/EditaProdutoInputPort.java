package br.com.fiap.hackathon.ponto.core.ports.in.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;

public interface EditaProdutoInputPort {

    ProdutoDTO editar(ProdutoDTO produto, Long id);
}
