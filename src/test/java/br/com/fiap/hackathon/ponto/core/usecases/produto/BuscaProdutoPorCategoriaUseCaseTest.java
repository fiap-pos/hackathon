package br.com.fiap.hackathon.ponto.core.usecases.produto;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.CategoriaEnum;
import br.com.fiap.hackathon.ponto.core.ports.in.produto.BuscaProdutoPorCategoriaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.produto.BuscaProdutoPorCategoriaOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static br.com.fiap.hackathon.ponto.utils.ProdutoHelper.getProdutoDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class BuscaProdutoPorCategoriaUseCaseTest {

    private BuscaProdutoPorCategoriaInputPort buscaProdutoPorCategoriaInputPort;

    @Mock
    BuscaProdutoPorCategoriaOutputPort buscaProdutoPorIdOutputPort;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscaProdutoPorCategoriaInputPort = new BuscaProdutoPorCategoriaUseCase(buscaProdutoPorIdOutputPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class buscaProdutoPorCategoriaUseCase {
        @Test
        void buscaProdutoPorCategoria() {
            var listaProdutos = List.of(getProdutoDTO());
            when(buscaProdutoPorIdOutputPort.buscarPorCategoria(CategoriaEnum.LANCHE)).thenReturn(listaProdutos);

            var produtosPorCategoria = buscaProdutoPorCategoriaInputPort.buscarPorCategoria(CategoriaEnum.LANCHE);

            assertThat(produtosPorCategoria)
                    .isNotNull()
                    .isNotEmpty()
                    .allSatisfy(produto -> {
                        assertThat(produto.categoria()).isEqualTo(listaProdutos.get(0).categoria());
                        assertThat(produto.nome()).isEqualTo(listaProdutos.get(0).nome());
                    });
        }
    }

}