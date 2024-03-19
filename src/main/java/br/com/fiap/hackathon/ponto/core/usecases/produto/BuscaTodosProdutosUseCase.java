package br.com.fiap.hackathon.ponto.core.usecases.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.produto.BuscaTodosProdutosInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.produto.BuscaTodosProdutosOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BuscaTodosProdutosUseCase implements BuscaTodosProdutosInputPort {

    BuscaTodosProdutosOutputPort buscaProdutoPorIdOutputPort;

    public BuscaTodosProdutosUseCase(BuscaTodosProdutosOutputPort buscaProdutoPorIdOutputPort) {
        this.buscaProdutoPorIdOutputPort = buscaProdutoPorIdOutputPort;
    }

    @Override
    public List<ProdutoDTO> buscartodos() {
        return buscaProdutoPorIdOutputPort.buscarTodos();
    }
}
