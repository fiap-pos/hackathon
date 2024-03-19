package br.com.fiap.hackathon.ponto.core.ports.out.produto;

import br.com.fiap.hackathon.ponto.core.dtos.AtualizaImagemProdutoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;

public interface AtualizaImagemProdutoOutputPort {

    ProdutoDTO atualizar(AtualizaImagemProdutoDTO imagenIn, Long id);
}
