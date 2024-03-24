package br.com.fiap.hackathon.ponto.adapters.gateways.mail.mappers;

import br.com.fiap.hackathon.ponto.adapters.gateways.mail.models.Attachment;
import br.com.fiap.hackathon.ponto.adapters.gateways.mail.models.Mail;
import br.com.fiap.hackathon.ponto.adapters.gateways.mail.models.MailId;
import br.com.fiap.hackathon.ponto.core.dtos.RelatorioPontoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class MailMapper {

    private final Logger logger = LoggerFactory.getLogger(MailMapper.class);
    private final String CONTENT_TYPE = "text/csv";

    public Mail toMailRelatorioPonto(RelatorioPontoDTO relatorioPontoDTO, String mailFrom, String mailFromName) {
        var from = new MailId(mailFrom, mailFromName);
        var to = new MailId(relatorioPontoDTO.email(), relatorioPontoDTO.matricula());
        var subject = getSubjectStatusPedido(relatorioPontoDTO);
        var text = getTextoRelatorioPonto(relatorioPontoDTO.matricula(), subject);
        var conteudo = getConteutoBase64(relatorioPontoDTO.nomeArquivoRelatorio());
        var nomeArquivo = relatorioPontoDTO.nomeArquivoRelatorio().replace("./", "");
        var attachment = new Attachment(conteudo, CONTENT_TYPE, nomeArquivo);

        return new Mail(from, List.of(to), subject, text, List.of(attachment));
    }

    private String getConteutoBase64(String filePath) {
        try {
            logger.info("Convertendo arquivo para Base64: " + filePath);
            String base64Encoded = convertFileToBase64(filePath);
            logger.info("Arquivo convertido para Base64 com sucesso: " + filePath);
            return base64Encoded;
        } catch (Exception e) {
            logger.error("Erro ao converter arquivo para Base64: " + e.getMessage());
        }
        return null;
    }

    private String convertFileToBase64(String filePath) {
        try {
            logger.info("Lendo arquivo: " + filePath);
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            logger.info("Arquivo lido com sucesso: " + filePath);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            logger.error("Erro ao ler arquivo: " + e.getMessage());
        }
        return null;
    }

    private String getSubjectStatusPedido(RelatorioPontoDTO relatorioPontoDTO) {
        return "Relatório de ponto " + relatorioPontoDTO.matricula() + " - "  + relatorioPontoDTO.mes() + "/" + relatorioPontoDTO.ano();
    }

    private String getTextoRelatorioPonto(String matricula, String subject) {
        return "Olá solicitante da matricula: " + matricula + " seu " + subject + ", está pronto !";
    }
}
