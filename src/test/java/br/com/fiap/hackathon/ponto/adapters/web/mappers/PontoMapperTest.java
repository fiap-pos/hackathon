package br.com.fiap.hackathon.ponto.adapters.web.mappers;

import br.com.fiap.hackathon.ponto.adapters.web.models.responses.PontoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPontoDTO;
import static org.assertj.core.api.Assertions.assertThat;

public class PontoMapperTest {
    private PontoMapper mapper;

    @BeforeEach
    void setup(){
        mapper = new PontoMapper();
    }

    @Test
    void dadoPontoDTO_DeveFazerMapper_RetornarPontoResponse(){
        var pontoDTO = getPontoDTO();
        var pontoResponse = mapper.toPontoResponse(pontoDTO);

        assertThat(pontoResponse).isNotNull();
        assertThat(pontoResponse.getId()).isEqualTo(pontoDTO.id());
        assertThat(pontoResponse.getMatricula()).isEqualTo(pontoDTO.matricula());
        assertThat(pontoResponse.getRegistro()).isEqualTo(pontoDTO.horaRegistro());
        assertThat(pontoResponse.getTipoRegistro()).isEqualTo(pontoDTO.tipoRegistro());
    }

    @Test
    void dadoListaPontoDTO_DeveFazerMapper_RetornarListaPontoResponse() {
        var listaPontoDTO = List.of(getPontoDTO());

        var listaPontoResponse = mapper.toPontoListResponse(listaPontoDTO);
        assertThat(listaPontoResponse)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(pontoResponse -> {
                    assertThat(pontoResponse).isNotNull().isInstanceOf(PontoResponse.class);
                    assertThat(pontoResponse.getId()).isEqualTo(listaPontoDTO.get(0).id());
                    assertThat(pontoResponse.getMatricula()).isEqualTo(listaPontoDTO.get(0).matricula());
                    assertThat(pontoResponse.getRegistro()).isEqualTo(listaPontoDTO.get(0).horaRegistro());
                });
    }
}
