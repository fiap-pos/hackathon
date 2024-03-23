package br.com.fiap.hackathon.ponto.adapters.repository.sqs;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RelatorioPontoSqsPublisher {

    private final Logger logger = LoggerFactory.getLogger(RelatorioPontoSqsPublisher.class);

    private ObjectMapper objectMapper;

    @Value("${aws.sqs.queues.relatorios}")
    private String filaRelatorios;

    private SqsTemplate sqsTemplate;

    public RelatorioPontoSqsPublisher(ObjectMapper objectMapper, SqsTemplate sqsTemplate) {
        this.objectMapper = objectMapper;
        this.sqsTemplate = sqsTemplate;
    }

    public void publicaFilaRelatorios(FilaRelatorioDTO filaRelatorioDTO) {
        try {
            logger.info("Publicando mensagem na fila de relatórios");
            sqsTemplate.send(filaRelatorios, objectMapper.writeValueAsString(filaRelatorioDTO));
            logger.info("Mensagem publicada com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao publicar mensagem na fila de relatórios", e);
        }
    }
}
