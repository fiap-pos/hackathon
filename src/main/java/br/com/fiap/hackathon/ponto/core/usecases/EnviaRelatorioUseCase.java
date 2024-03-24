package br.com.fiap.hackathon.ponto.core.usecases;

import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.EnviaRelatorioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.EnviaRelatorioFilaRelatoriosOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.NotificaSolicitanteRelatorioPontoOuputPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnviaRelatorioUseCase implements EnviaRelatorioInputPort {

    Logger logger = LoggerFactory.getLogger(EnviaRelatorioUseCase.class);

    private final EnviaRelatorioFilaRelatoriosOutputPort enviaRelatorioFilaRelatoriosOutputPort;

    @Override
    public String enviaRelatorioPontoParaFilaRelatorios(RelatorioPontoDTO relatorioPontoDTO) {
        try {
            logger.info("Enviando relatório de ponto para fila de relatórios");
            var response = enviaRelatorioFilaRelatoriosOutputPort.enviarRelatorio(relatorioPontoDTO);
            logger.info("Relatório de ponto enviado para fila de relatórios");

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar relatório de ponto", e);
        }

    }
}
