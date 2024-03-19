package br.com.fiap.hackathon.ponto.core.usecases.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.CategoriaEnum;
import br.com.fiap.hackathon.ponto.core.ports.in.produto.BuscaProdutoPorCategoriaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.produto.BuscaProdutoPorCategoriaOutputPort;

import java.util.List;

public class BuscaProdutoPorCategoriaUseCase implements BuscaProdutoPorCategoriaInputPort {

    BuscaProdutoPorCategoriaOutputPort buscaProdutoPorIdOutputPort;

    public BuscaProdutoPorCategoriaUseCase(BuscaProdutoPorCategoriaOutputPort buscaProdutoPorIdOutputPort) {
        this.buscaProdutoPorIdOutputPort = buscaProdutoPorIdOutputPort;
    }

    @Override
    public List<ProdutoDTO> buscarPorCategoria(CategoriaEnum categoriaEnum) {
        return buscaProdutoPorIdOutputPort.buscarPorCategoria(categoriaEnum);
    }
}
