package br.com.fiap.hackathon.ponto.core.ports.in.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import java.util.List;

public interface BuscaTodosProdutosInputPort {

    List<ProdutoDTO> buscartodos();
}
