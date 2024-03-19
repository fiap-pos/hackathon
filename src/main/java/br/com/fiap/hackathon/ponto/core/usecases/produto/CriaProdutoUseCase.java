package br.com.fiap.hackathon.ponto.core.usecases.produto;

import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.produto.CriaProdutoInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.produto.CriaProdutoOutputPort;
import org.springframework.stereotype.Component;

@Component
public class CriaProdutoUseCase implements CriaProdutoInputPort {

    CriaProdutoOutputPort criaProdutoOutputPort;

    public CriaProdutoUseCase(CriaProdutoOutputPort criaProdutoOutputPort) {
        this.criaProdutoOutputPort = criaProdutoOutputPort;
    }

    @Override
    public ProdutoDTO criar(ProdutoDTO produtoIn) {
        return criaProdutoOutputPort.criar(produtoIn);
    }
}
