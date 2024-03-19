package br.com.fiap.hackathon.ponto.core.usecases.produto;

import br.com.fiap.hackathon.ponto.core.dtos.AtualizaImagemProdutoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.produto.AtualizaImagemProdutoInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.produto.AtualizaImagemProdutoOutputPort;
import org.springframework.stereotype.Component;

@Component
public class AtualizaImagemProdutoUseCase implements AtualizaImagemProdutoInputPort {

    AtualizaImagemProdutoOutputPort atualizaImagemProdutoOutputPort;

    public AtualizaImagemProdutoUseCase(AtualizaImagemProdutoOutputPort salvaProdutoOutputPort) {
        this.atualizaImagemProdutoOutputPort = salvaProdutoOutputPort;
    }

    @Override
    public ProdutoDTO atualizar(AtualizaImagemProdutoDTO imagemIn, Long id) {
        return atualizaImagemProdutoOutputPort.atualizar(imagemIn, id);
    }
}
