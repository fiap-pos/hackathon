package br.com.fiap.hackathon.ponto.adapters.web.mappers;

import br.com.fiap.hackathon.ponto.adapters.web.models.responses.ProdutoResponse;
import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import org.springframework.stereotype.Component;

@Component("produtoMapperWeb")
public class ProdutoMapper {

    public ProdutoResponse toProdutoResponse(ProdutoDTO produtoOut) {
        return new ProdutoResponse(produtoOut.id(), produtoOut.nome(), produtoOut.categoria(),
                produtoOut.preco(), produtoOut.descricao(), produtoOut.imagem());
    }

}
