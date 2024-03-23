package br.com.fiap.hackathon.ponto.adapters.messages.listeners;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.core.ports.out.GeraRelatorioOutputPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
@RequiredArgsConstructor
public class FilaRelatorisListener {

    private final Logger logger = LoggerFactory.getLogger(FilaRelatorisListener.class);

    private final GeraRelatorioOutputPort geraRelatorioOutputPort;

    private final ObjectMapper objectMapper;


    @SqsListener("${aws.sqs.queues.relatorios}")
    public void listen(Message menssagem) throws JsonProcessingException {
        try {
            logger.info("Recebendo mensagem da fila de relatórios");
            var filaRelatorioDTO = objectMapper.readValue(menssagem.body(), FilaRelatorioDTO.class);
            logger.info("Mensagem recebida da fila de relatórios com sucesso");
            geraRelatorio(filaRelatorioDTO);
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem da fila de relatórios", e);
        }
    }

    private void geraRelatorio(FilaRelatorioDTO filaRelatorioDTO) {
        try {
            logger.info("Gerando relatório...");
            geraRelatorioOutputPort.geraRelatorio(
                    filaRelatorioDTO.matricula(),
                    filaRelatorioDTO.mes(),
                    filaRelatorioDTO.ano()
            );
            logger.info("Relatório gerado com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao gerar relatório", e);
        }
    }
}
