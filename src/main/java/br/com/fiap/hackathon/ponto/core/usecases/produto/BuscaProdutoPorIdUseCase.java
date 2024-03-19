package br.com.fiap.hackathon.ponto.core.usecases.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.produto.BuscaProdutoPorIdInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.produto.BuscaProdutoPorIdOutputPort;
import org.springframework.stereotype.Component;

@Component
public class BuscaProdutoPorIdUseCase implements BuscaProdutoPorIdInputPort {

    BuscaProdutoPorIdOutputPort buscaProdutoPorIdOutputPort;

    public BuscaProdutoPorIdUseCase(BuscaProdutoPorIdOutputPort buscaProdutoPorIdOutputPort) {
        this.buscaProdutoPorIdOutputPort = buscaProdutoPorIdOutputPort;
    }

    @Override
    public ProdutoDTO buscarPorId(Long id) {
        return buscaProdutoPorIdOutputPort.buscarPorId(id);
    }
}
