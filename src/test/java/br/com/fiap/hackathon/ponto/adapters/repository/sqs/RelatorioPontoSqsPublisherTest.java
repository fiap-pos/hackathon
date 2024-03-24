package br.com.fiap.hackathon.ponto.adapters.repository.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.UUID;

import static br.com.fiap.hackathon.ponto.utils.RelatorioHelper.getFilaRelatorioDTO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RelatorioPontoSqsPublisherTest {
    private RelatorioPontoSqsPublisher relatorioPontoSqsPublisher;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private SqsTemplate sqsTemplate;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        relatorioPontoSqsPublisher = new RelatorioPontoSqsPublisher(objectMapper, sqsTemplate);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void testEnviarParaFilaPedidoCriado() throws JsonProcessingException {
        UUID messageId = UUID.randomUUID();
        GenericMessage<String> message = new GenericMessage<>("Payload", new HashMap<>());
        var filaRelatorioDTO = getFilaRelatorioDTO();

        when(objectMapper.writeValueAsString(filaRelatorioDTO)).thenReturn("Payload");

        when(sqsTemplate.send(anyString(), anyString()))
                .thenReturn(new SendResult<>(messageId, "https://config.us-east-2.amazonaws.com", message, new HashMap<>()));

        relatorioPontoSqsPublisher.publicaFilaRelatorios(filaRelatorioDTO);

        verify(objectMapper).writeValueAsString(filaRelatorioDTO);
        verify(sqsTemplate).send(Mockito.<String>any(), eq("Payload"));
    }

    @Test
    void testEnviarParaFilaPedidoCriadoThrowsJsonProcessingException() throws JsonProcessingException {
        var filaRelatorioDTO = getFilaRelatorioDTO();

        when(objectMapper.writeValueAsString(filaRelatorioDTO)).thenThrow(JsonProcessingException.class);

        assertThrows(JsonProcessingException.class, () -> relatorioPontoSqsPublisher.publicaFilaRelatorios(filaRelatorioDTO));

        verify(objectMapper).writeValueAsString(filaRelatorioDTO);
    }

}