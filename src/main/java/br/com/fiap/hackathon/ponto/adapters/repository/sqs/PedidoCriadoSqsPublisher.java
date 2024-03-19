package br.com.fiap.hackathon.ponto.adapters.repository.sqs;

import br.com.fiap.hackathon.ponto.adapters.gateways.producao.models.FilaProducaoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoCriadoSqsPublisher {

    private ObjectMapper objectMapper;

    @Value("${aws.sqs.queues.relatorios}")
    private String filaPedidoCriado;

    private SqsTemplate sqsTemplate;

    public PedidoCriadoSqsPublisher(ObjectMapper objectMapper, SqsTemplate sqsTemplate) {
        this.objectMapper = objectMapper;
        this.sqsTemplate = sqsTemplate;
    }

    public void publicaFilaPedidoCriado(FilaProducaoDTO filaProducaoDTO) throws JsonProcessingException {
        sqsTemplate.send(filaPedidoCriado, objectMapper.writeValueAsString(filaProducaoDTO));
    }
}
