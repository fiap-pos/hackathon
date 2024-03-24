package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.PontoRequest;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.EnviaRelatorioInputPort;
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

import static br.com.fiap.hackathon.ponto.utils.JsonToStringHelper.asJsonString;
import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPontoDTO;
import static br.com.fiap.hackathon.ponto.utils.RelatorioHelper.getRelatorioPontoDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PontoControllerTest {
    private MockMvc mockMvc;

    @Mock
    RegistraPontoInputPort registraPontoInputPort;

    @Mock
    BuscaStatusDiaInputPort buscaStatusDiaInputPort;

    @Mock
    EnviaRelatorioInputPort enviaRelatorioInputPort;

    PontoMapper mapper;
    AutoCloseable mock;
    PontoRequest relatorioPontoRequest;

    @BeforeEach
    void setUp() {
        relatorioPontoRequest = new PontoRequest("12345");
        this.mapper = new PontoMapper();
        mock = MockitoAnnotations.openMocks(this);
        PontoController pontoController = new PontoController(
                registraPontoInputPort,
                buscaStatusDiaInputPort,
                enviaRelatorioInputPort,
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

        when(buscaStatusDiaInputPort.buscaStatusDiaPorMatricula(getPontoDTO().matricula())).thenReturn(Collections.singletonList(pontoDTO));

        ResultActions result = mockMvc.perform(get("/ponto/{matricula}", pontoDTO.matricula()).contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        verify(buscaStatusDiaInputPort, times(1)).buscaStatusDiaPorMatricula(getPontoDTO().matricula());
        verifyNoMoreInteractions(buscaStatusDiaInputPort);
    }

    @Test
    void gerarRelatorio() throws Exception {
        var relatorioPontoDTO = getRelatorioPontoDTO();

        when(enviaRelatorioInputPort.enviaRelatorioPonto(any(RelatorioPontoDTO.class))).thenReturn(asJsonString((relatorioPontoDTO)));

        ResultActions result = mockMvc.perform(post("/ponto/relatorio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(relatorioPontoRequest))
        );

        result.andExpect(status().isOk());

        verify(enviaRelatorioInputPort, times(1)).enviaRelatorioPonto(any(RelatorioPontoDTO.class));
        verifyNoMoreInteractions(enviaRelatorioInputPort);
    }

    @Test
    void registrarPonto() throws Exception {
        var pontoDTO = getPontoDTO();

        when(registraPontoInputPort.registrar(any(PontoDTO.class))).thenReturn(pontoDTO);

        ResultActions result = mockMvc.perform(post("/ponto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(relatorioPontoRequest))
        );

        result.andExpect(status().isCreated());

        verify(registraPontoInputPort, times(1)).registrar(any(PontoDTO.class));
        verifyNoMoreInteractions(registraPontoInputPort);
    }
}
