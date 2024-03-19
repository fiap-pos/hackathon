package br.com.fiap.hackathon.ponto.core.ports.out.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.CategoriaEnum;
import java.util.List;

public interface BuscaProdutoPorCategoriaOutputPort {

    List<ProdutoDTO> buscarPorCategoria(CategoriaEnum categoriaEnum);
}
