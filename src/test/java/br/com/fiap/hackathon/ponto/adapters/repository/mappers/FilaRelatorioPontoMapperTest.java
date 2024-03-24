package br.com.fiap.hackathon.ponto.adapters.repository.mappers;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.fiap.hackathon.ponto.utils.RelatorioHelper.getRelatorioPontoDTO;
import static org.assertj.core.api.Assertions.assertThat;

class FilaRelatorioPontoMapperTest {

    private FilaRelatorioPontoMapper filaRelatorioMapper;

    @BeforeEach
    void setUp() {
        this.filaRelatorioMapper = new FilaRelatorioPontoMapper();
    }

    @Test
    void dadoRelatorioPontoDTO_DeveFazerMapper_RetornarFilaRelatorioDTO() {
        var relatorioDTO = getRelatorioPontoDTO();

        var filaRelatorioDTO = filaRelatorioMapper.toFilaRelatorioPontoDTO(relatorioDTO);

        assertThat(filaRelatorioDTO).isNotNull().isInstanceOf(FilaRelatorioDTO.class);
        assertThat(filaRelatorioDTO.matricula()).isEqualTo(relatorioDTO.matricula());
        assertThat(filaRelatorioDTO.ano()).isEqualTo(relatorioDTO.ano());
        assertThat(filaRelatorioDTO.mes()).isEqualTo(relatorioDTO.mes());
    }
}