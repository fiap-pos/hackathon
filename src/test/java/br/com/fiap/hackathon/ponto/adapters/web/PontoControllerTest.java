package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.PontoRequest;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.RelatorioPontoRequest;
import br.com.fiap.hackathon.ponto.core.domain.entities.AuthToken;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaUsuarioInputPort;
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
import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getUsuario;
import static br.com.fiap.hackathon.ponto.utils.RelatorioHelper.getRelatorioPontoDTO;
import static br.com.fiap.hackathon.ponto.utils.RelatorioHelper.getUsuarioDTO;
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
    BuscaUsuarioInputPort buscaUsuarioInputPort;

    @Mock
    EnviaRelatorioInputPort enviaRelatorioInputPort;

    PontoMapper mapper;
    AutoCloseable mock;
    PontoRequest pontoRequest;
    RelatorioPontoRequest relatorioPontoRequest;
    AuthToken authToken;

    @BeforeEach
    void setUp() {
        pontoRequest = new PontoRequest("12345");
        relatorioPontoRequest = new RelatorioPontoRequest(2, 2024);
        this.mapper = new PontoMapper();
        mock = MockitoAnnotations.openMocks(this);
        authToken = new AuthToken("ef9fcc24-fed5-4e9e-9c64-3efcc7b19f38");
        PontoController pontoController = new PontoController(
                registraPontoInputPort,
                buscaStatusDiaInputPort,
                buscaUsuarioInputPort,
                enviaRelatorioInputPort,
                mapper,
                authToken
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
        var usuario = getUsuario();

        var token = authToken.criar(usuario).accessToken();

        when(buscaStatusDiaInputPort.buscaStatusDiaPorMatricula(getPontoDTO().matricula())).thenReturn(Collections.singletonList(pontoDTO));

        ResultActions result = mockMvc.perform(get("/ponto", pontoDTO.matricula())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token));

        result.andExpect(status().isOk());

        verify(buscaStatusDiaInputPort, times(1)).buscaStatusDiaPorMatricula(getPontoDTO().matricula());
        verifyNoMoreInteractions(buscaStatusDiaInputPort);
    }

    @Test
    void gerarRelatorio() throws Exception {
        var relatorioPontoDTO = getRelatorioPontoDTO();
        var usuarioDTO = getUsuarioDTO();
        var token = authToken.criar(getUsuario()).accessToken();

        when(buscaUsuarioInputPort.buscaUsuarioPorMatricula(any(String.class))).thenReturn(usuarioDTO);
        when(enviaRelatorioInputPort.enviaRelatorioPontoParaFilaRelatorios(any(RelatorioPontoDTO.class))).thenReturn(asJsonString(relatorioPontoDTO));

        when(enviaRelatorioInputPort.enviaRelatorioPontoParaFilaRelatorios(any(RelatorioPontoDTO.class))).thenReturn(asJsonString((relatorioPontoDTO)));
        when(buscaUsuarioInputPort.buscaUsuarioPorMatricula(any(String.class))).thenReturn(usuarioDTO);

        ResultActions result = mockMvc.perform(post("/ponto/relatorio")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(asJsonString(relatorioPontoRequest))
        );

        result.andExpect(status().isOk());

        verify(enviaRelatorioInputPort, times(1)).enviaRelatorioPontoParaFilaRelatorios(any(RelatorioPontoDTO.class));
        verifyNoMoreInteractions(enviaRelatorioInputPort);
    }

    @Test
    void registrarPonto() throws Exception {
        var pontoDTO = getPontoDTO();
        var token = authToken.criar(getUsuario()).accessToken();

        when(registraPontoInputPort.registrar(any(PontoDTO.class))).thenReturn(pontoDTO);

        ResultActions result = mockMvc.perform(post("/ponto")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(asJsonString(pontoRequest))
        );

        result.andExpect(status().isCreated());

        verify(registraPontoInputPort, times(1)).registrar(any(PontoDTO.class));
        verifyNoMoreInteractions(registraPontoInputPort);
    }
}
