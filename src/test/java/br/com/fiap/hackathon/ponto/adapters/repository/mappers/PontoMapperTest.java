package br.com.fiap.hackathon.ponto.adapters.repository.mappers;

import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;

import java.util.List;

import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPonto;
import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPontoDTO;
import static org.assertj.core.api.Assertions.assertThat;

public class PontoMapperTest {
    private PontoMapper produtoMapper;

    @BeforeEach
    void setUp() {
        this.produtoMapper = new PontoMapper();
    }

    @Test
    void dadoPontoDTO_DeveFazerMapper_RetornarPonto() {
        var produtoDTO = getPontoDTO();

        var produto = produtoMapper.toPonto(produtoDTO);

        assertThat(produto).isNotNull().isInstanceOf(Ponto.class);
        assertThat(produto.getRegistro()).isEqualTo(produtoDTO.horaRegistro());
        assertThat(produto.getMatricula()).isEqualTo(produtoDTO.matricula());
        assertThat(produto.getTipoRegistro()).isEqualTo(produtoDTO.tipoRegistro());
    }

    @Test
    void dadoPonto_DeveFazerMapper_RetornarPontoDTO() {
        var produto = getPonto();

        var produtoDTO = produtoMapper.toPontoDTO(produto);

        assertThat(produtoDTO).isNotNull().isInstanceOf(PontoDTO.class);
        assertThat(produtoDTO.horaRegistro()).isEqualTo(produto.getRegistro());
        assertThat(produtoDTO.tipoRegistro()).isEqualTo(produto.getTipoRegistro());
        assertThat(produtoDTO.matricula()).isEqualTo(produto.getMatricula());
    }
    @Test
    void dadoListaPonto_DeveFazerMapper_RetornarListaPontoDTO() {
        var produtoList = List.of(getPonto());

        var produtoDTO = produtoMapper.toPontoDTOList(produtoList);

        assertThat(produtoDTO.get(0)).isNotNull().isInstanceOf(PontoDTO.class);
        assertThat(produtoDTO.get(0).horaRegistro()).isEqualTo(produtoList.get(0).getRegistro());
        assertThat(produtoDTO.get(0).tipoRegistro()).isEqualTo(produtoList.get(0).getTipoRegistro());
        assertThat(produtoDTO.get(0).matricula()).isEqualTo(produtoList.get(0).getMatricula());
    }
}
