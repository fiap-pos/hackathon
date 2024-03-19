package br.com.fiap.hackathon.ponto.core.ports.in.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.CategoriaEnum;
import java.util.List;

public interface BuscaProdutoPorCategoriaInputPort {

    List<ProdutoDTO> buscarPorCategoria(CategoriaEnum categoriaEnum);
}
