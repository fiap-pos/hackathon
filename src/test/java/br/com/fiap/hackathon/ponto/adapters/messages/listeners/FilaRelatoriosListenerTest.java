package br.com.fiap.hackathon.ponto.adapters.messages.listeners;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.core.ports.out.GeraRelatorioOutputPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.sqs.model.Message;

import static br.com.fiap.hackathon.ponto.utils.RelatorioHelper.getFilaRelatorioDTO;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FilaRelatoriosListenerTest {
    private FilaRelatoriosListener filaRelatoriosListener;

    @Mock
    private GeraRelatorioOutputPort geraRelatorioOutputPort;

    @Mock
    private ObjectMapper objectMapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        filaRelatoriosListener = new FilaRelatoriosListener(geraRelatorioOutputPort, objectMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void listenTest() throws JsonProcessingException {
        var filaRelatorioDTO = new FilaRelatorioDTO("12345", 2, 2024);
        var filaRelatorioDTOJson = getPedidoJson(filaRelatorioDTO);
        var message = mock(Message.class);
        var filaRelatorioDTORetorno = getFilaRelatorioDTO();

        when(objectMapper.readValue(filaRelatorioDTOJson, FilaRelatorioDTO.class)).thenReturn(filaRelatorioDTO);
        when(geraRelatorioOutputPort.geraRelatorio(
                filaRelatorioDTO.matricula(),
                filaRelatorioDTO.mes(), filaRelatorioDTO.ano())).thenReturn(String.valueOf(filaRelatorioDTORetorno));
        when(message.body()).thenReturn(filaRelatorioDTOJson);

        filaRelatoriosListener.listen(message);

        verify(geraRelatorioOutputPort, times(1))
                .geraRelatorio(filaRelatorioDTO.matricula(), filaRelatorioDTO.mes(), filaRelatorioDTO.ano());
    }

    private String getPedidoJson(FilaRelatorioDTO filaRelatorioDTO) throws JsonProcessingException {
        var objMapper = new ObjectMapper();
        objMapper.findAndRegisterModules();
        return objMapper.writeValueAsString(filaRelatorioDTO);
    }

}