package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.ports.in.RegistraPontoInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.RegistraPontoOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPontoDTO;
import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPontoDTO_somenteMatricula;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RegistraPontoUseCaseTest {

    private RegistraPontoInputPort registraPontoInputPort;

    @Mock
    RegistraPontoOutputPort registraPontoOutputPort;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        registraPontoInputPort = new RegistraPontoUseCase(registraPontoOutputPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void registrar() {
        var pontoRequest = getPontoDTO_somenteMatricula();
        var pontoResponse = getPontoDTO();

        when(registraPontoOutputPort.registrar(pontoRequest)).thenReturn(pontoResponse);

        var ponto = registraPontoInputPort.registrar(pontoRequest);

        assertThat(ponto).isNotNull();
    }

}