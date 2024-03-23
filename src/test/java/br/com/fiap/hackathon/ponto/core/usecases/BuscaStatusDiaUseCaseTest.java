package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.BuscaStatusDiaOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPontoDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class BuscaStatusDiaUseCaseTest {

    private BuscaStatusDiaInputPort buscaStatusDiaInputPort;

    @Mock
    BuscaStatusDiaOutputPort buscaStatusDiaOutputPort;
    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscaStatusDiaInputPort = new BuscaStatusDiaUseCase(buscaStatusDiaOutputPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void buscaStatusDia() {
        var pontoDTO = getPontoDTO();
        var pontoDTOList = List.of(getPontoDTO());
        when(buscaStatusDiaOutputPort.buscaStatusDiaPorMatricula(getPontoDTO().matricula())).thenReturn(pontoDTOList);

        var pontoList = buscaStatusDiaInputPort.buscaStatusDiaPorMatricula(pontoDTO.matricula());

        assertThat(pontoList).isNotNull().isNotEmpty()
                .allSatisfy(pontoBuscado -> {
                    assertThat(pontoBuscado.id()).isEqualTo(pontoDTOList.get(0).id());
                    assertThat(pontoBuscado.horaRegistro()).isEqualTo(pontoDTOList.get(0).horaRegistro());
                    assertThat(pontoBuscado.matricula()).isEqualTo(pontoDTOList.get(0).matricula());
                    assertThat(pontoBuscado.tipoRegistro()).isEqualTo(pontoDTOList.get(0).tipoRegistro());
                });
        verify(buscaStatusDiaOutputPort, times(1)).buscaStatusDiaPorMatricula(pontoDTO.matricula());
        verifyNoMoreInteractions(buscaStatusDiaOutputPort);
    }
}
