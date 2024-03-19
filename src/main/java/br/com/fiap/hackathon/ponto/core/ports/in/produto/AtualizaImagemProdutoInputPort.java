package br.com.fiap.hackathon.ponto.core.ports.in.produto;

import br.com.fiap.hackathon.ponto.core.dtos.AtualizaImagemProdutoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;

public interface AtualizaImagemProdutoInputPort {

    ProdutoDTO atualizar(AtualizaImagemProdutoDTO imagemIn, Long id);
}
