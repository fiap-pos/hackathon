package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.ports.in.EnviaRelatorioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.EnviaRelatorioFilaRelatoriosOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.fiap.hackathon.ponto.utils.RelatorioHelper.getMensagem;
import static br.com.fiap.hackathon.ponto.utils.RelatorioHelper.getRelatorioPontoDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class EnviaRelatorioUseCaseTest {
    private EnviaRelatorioInputPort enviaRelatorioFilaRelatoriosInputPort;
    @Mock
    EnviaRelatorioFilaRelatoriosOutputPort enviaRelatorioFilaRelatoriosOutputPort;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        enviaRelatorioFilaRelatoriosInputPort = new EnviaRelatorioUseCase(enviaRelatorioFilaRelatoriosOutputPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void enviaRelatorioPonto() {
        var relatorioPontoDTO = getRelatorioPontoDTO();
        when(enviaRelatorioFilaRelatoriosOutputPort.enviarRelatorio(relatorioPontoDTO)).thenReturn(getMensagem());
        var retorno = enviaRelatorioFilaRelatoriosInputPort.enviaRelatorioPontoParaFilaRelatorios(relatorioPontoDTO);
        assertThat(retorno).isEqualTo(getMensagem());
    }
}