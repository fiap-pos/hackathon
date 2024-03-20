package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.RegistraPontoInputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPontoDTO;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PontoControllerTest {
    private MockMvc mockMvc;

    @Mock
    RegistraPontoInputPort registraPontoInputPort;

    @Mock
    BuscaStatusDiaInputPort buscaStatusDiaInputPort;
    PontoMapper mapper;
    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        this.mapper = new PontoMapper();
        mock = MockitoAnnotations.openMocks(this);
        PontoController pontoController = new PontoController(
                registraPontoInputPort,
                buscaStatusDiaInputPort,
                mapper
        );

        mockMvc = MockMvcBuilders.standaloneSetup(pontoController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void buscarStatusDia() throws Exception {
        var pontoDTO = getPontoDTO();

        when(buscaStatusDiaInputPort.buscaStatusDia()).thenReturn(Collections.singletonList(pontoDTO));

        ResultActions result = mockMvc.perform(get("/ponto").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        verify(buscaStatusDiaInputPort, times(1)).buscaStatusDia();
        verifyNoMoreInteractions(buscaStatusDiaInputPort);
    }

}
