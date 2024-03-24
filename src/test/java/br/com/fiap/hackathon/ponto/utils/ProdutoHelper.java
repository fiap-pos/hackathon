package br.com.fiap.hackathon.ponto.utils;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Produto;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.ProdutoRequest;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.CategoriaEnum;
import br.com.fiap.hackathon.ponto.core.dtos.AtualizaImagemProdutoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.ProdutoDTO;
import org.instancio.Instancio;

import java.math.BigDecimal;

public abstract class ProdutoHelper {

    private static final Long ID = 1L;

    private static final String NOME = "HAMBURGER ANGUS";
    private static final CategoriaEnum CATEGORIA = CategoriaEnum.LANCHE;
    private static final BigDecimal PRECO = BigDecimal.valueOf(35.90);
    private static final String DESCRICAO = "Hamburger Angus 200mg de carne";

    public static ProdutoDTO getProdutoDTO() {
        return new ProdutoDTO(ID, NOME, CATEGORIA, PRECO, DESCRICAO, null);
    }

    public static ProdutoDTO getProdutoDTO(byte[] imagem) {
        return new ProdutoDTO(ID, NOME, CATEGORIA, PRECO, DESCRICAO, imagem);
    }

    public static Produto getProduto() {
        return new Produto(ID, NOME, CATEGORIA, PRECO, DESCRICAO);
    }

    public static Produto getProdutoSemID() {
        return new Produto(null, NOME, CATEGORIA, PRECO, DESCRICAO);
    }

    public static ProdutoRequest getProdutoRequest(String nome, CategoriaEnum categoria, BigDecimal preco, String descricao) {
        return new ProdutoRequest(nome, categoria, preco, descricao);
    }

    public static AtualizaImagemProdutoDTO getAtualizaImagemProdutoDTO() {
        return Instancio.create(AtualizaImagemProdutoDTO.class);
    }
}
