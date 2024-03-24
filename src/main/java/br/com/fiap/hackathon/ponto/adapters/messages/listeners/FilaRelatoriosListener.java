package br.com.fiap.hackathon.ponto.adapters.messages.listeners;

import br.com.fiap.hackathon.ponto.adapters.gateways.models.FilaRelatorioDTO;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.FilaRelatorioPontoMapper;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.out.GeraRelatorioOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.NotificaSolicitanteRelatorioPontoOuputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
@RequiredArgsConstructor
public class FilaRelatoriosListener {

    private final Logger logger = LoggerFactory.getLogger(FilaRelatoriosListener.class);

    private final GeraRelatorioOutputPort geraRelatorioOutputPort;

    private final ObjectMapper objectMapper;

    private final NotificaSolicitanteRelatorioPontoOuputPort notificaSolicitanteRelatorioPontoOuputPort;

    private final FilaRelatorioPontoMapper filaRelatorioPontoMapper;

    @SqsListener("${aws.sqs.queues.relatorios}")
    public void listen(Message menssagem) {
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
            String nomeArquivoRelatorio = geraRelatorioOutputPort.geraRelatorio(
                    filaRelatorioDTO.matricula(),
                    filaRelatorioDTO.mes(),
                    filaRelatorioDTO.ano()
            );
            logger.info("Relatório gerado com sucesso");

            var relatorioPontoDTO = filaRelatorioPontoMapper.toRelatorioPontoDTO(filaRelatorioDTO, nomeArquivoRelatorio);
            notificaSolicitanteDoRelatorio(relatorioPontoDTO);

            geraRelatorioOutputPort.deleteRelatorio(nomeArquivoRelatorio);
        } catch (Exception e) {
            logger.error("Erro ao gerar relatório", e);
        }
    }

    private void notificaSolicitanteDoRelatorio(RelatorioPontoDTO relatorioPontoDTO) {
        try {
            logger.info("Notificando solicitante do relatório de ponto");
            notificaSolicitanteRelatorioPontoOuputPort.notificaClienteStatusPedido(relatorioPontoDTO);
            logger.info("Solicitante do relatório de ponto notificado com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao notificar solicitante do relatório de ponto", e);
        }
    }
}
