package br.com.fiap.hackathon.ponto.core.ports.out.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import java.util.List;

public interface BuscaTodosProdutosOutputPort {

    List<ProdutoDTO> buscarTodos();
}
