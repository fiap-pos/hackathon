package br.com.fiap.hackathon.ponto.core.usecases.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.produto.RemoveProdutoInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.produto.RemoveProdutoOutputPort;
import org.springframework.stereotype.Component;

@Component
public class RemoveProdutoUseCase implements RemoveProdutoInputPort {

    RemoveProdutoOutputPort removeProdutoOutputPort;

    public RemoveProdutoUseCase(RemoveProdutoOutputPort removeProdutoOutputPort) {
        this.removeProdutoOutputPort = removeProdutoOutputPort;
    }

    @Override
    public ProdutoDTO remover(Long id) {
        return removeProdutoOutputPort.remover(id);
    }
}
