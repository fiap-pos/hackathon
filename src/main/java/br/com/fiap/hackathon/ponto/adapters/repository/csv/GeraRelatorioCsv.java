package br.com.fiap.hackathon.ponto.adapters.repository.csv;

import br.com.fiap.hackathon.ponto.adapters.repository.jpa.PontoJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import br.com.fiap.hackathon.ponto.core.ports.out.GeraRelatorioOutputPort;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GeraRelatorioCsv implements GeraRelatorioOutputPort {

    private final PontoJpaRepository pontoJpaRepository;

    @Value("${relatorio.csv}")
    private String nomeArquivo;

    @Override
    public String geraRelatorio(String matricula, int mes, int ano) {
        LocalDateTime inicio = LocalDateTime.of(ano, mes, 1, 0, 0);
        LocalDateTime fim = getFimDoMes(inicio);
        String nomeRelatorioCSV = formatarNomeArquivo(matricula, mes, ano);
        return geraRelatorioCSV(matricula, inicio, fim, nomeRelatorioCSV);
    }

    private LocalDateTime getFimDoMes(LocalDateTime inicio) {
        return inicio.withDayOfMonth(inicio.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59);
    }

    private String formatarNomeArquivo(String matricula, int mes, int ano) {
        return nomeArquivo.replace("{matricula}", matricula)
                .replace("{mes}", String.format("%02d", mes))
                .replace("{ano}", String.valueOf(ano));
    }

    private String geraRelatorioCSV(String matricula, LocalDateTime inicio, LocalDateTime fim, String nomeRelatorioCSV) {
        List<Ponto> registros = pontoJpaRepository.findByMatriculaAndRegistroBetween(matricula, inicio, fim);

        Map<LocalDate, List<Ponto>> registrosPorDia = agruparRegistrosPorDia(registros);

        List<String> headerList = construirHeader(registrosPorDia);

        try (CSVWriter writer = new CSVWriter(new FileWriter(nomeRelatorioCSV))) {
            writer.writeNext(headerList.toArray(new String[0]));
            registrosPorDia.forEach((dia, pontosDia) -> writer.writeNext(gerarLinha(dia, pontosDia, headerList.size()).toArray(new String[0])));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nomeRelatorioCSV;
    }

    private Map<LocalDate, List<Ponto>> agruparRegistrosPorDia(List<Ponto> registros) {
        return registros.stream()
                .collect(Collectors.groupingBy(p -> p.getRegistro().toLocalDate(), LinkedHashMap::new, Collectors.toList()));
    }

    private List<String> construirHeader(Map<LocalDate, List<Ponto>> registrosPorDia) {
        int maxColunas = calcularMaxColunas(registrosPorDia);
        List<String> headerList = new ArrayList<>();

        headerList.add("Dia");
        for (int i = 0; i < maxColunas; i++) {
            headerList.add("Entrada");
            headerList.add("Saída");
        }
        headerList.add("Horas Trabalhadas");

        return headerList;
    }

    private int calcularMaxColunas(Map<LocalDate, List<Ponto>> registrosPorDia) {
        int maxEntradas = getMaxTipoRegistro(registrosPorDia, TipoRegistroEnum.ENTRADA);
        int maxSaidas = getMaxTipoRegistro(registrosPorDia, TipoRegistroEnum.SAIDA);
        return Math.max(maxEntradas, maxSaidas);
    }

    private int getMaxTipoRegistro(Map<LocalDate, List<Ponto>> registrosPorDia, TipoRegistroEnum tipo) {
        return registrosPorDia.values().stream()
                .mapToInt(pontosDia -> (int) pontosDia.stream().filter(p -> p.getTipoRegistro() == tipo).count())
                .max().orElse(0);
    }

    private List<String> gerarLinha(LocalDate dia, List<Ponto> pontosDia, int totalColunas) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<String> linha = new ArrayList<>();
        linha.add(dia.toString());
        Duration totalHoras = calcularTotalHoras(pontosDia);
        pontosDia.forEach(ponto -> {
            LocalDateTime horario = ponto.getRegistro();
            if (ponto.getTipoRegistro() == TipoRegistroEnum.ENTRADA || ponto.getTipoRegistro() == TipoRegistroEnum.SAIDA) {
                linha.add(horario.format(timeFormatter));
            }
        });

        // Preenchendo com vazios caso não haja registro suficiente para preencher todas as colunas de entrada e saída
        while (linha.size() < totalColunas - 1) {
            linha.add("");
        }

        // Adicionando o total de horas trabalhadas no final
        long horas = totalHoras.toHours();
        long minutos = totalHoras.toMinutesPart();
        linha.add(String.format("%02d:%02d", horas, minutos));

        return linha;
    }

    private Duration calcularTotalHoras(List<Ponto> pontosDia) {
        Duration totalHoras = Duration.ZERO;
        LocalDateTime ultimaEntrada = null;

        for (Ponto ponto : pontosDia) {
            if (ponto.getTipoRegistro() == TipoRegistroEnum.ENTRADA) {
                ultimaEntrada = ponto.getRegistro();
            } else if (ponto.getTipoRegistro() == TipoRegistroEnum.SAIDA && ultimaEntrada != null) {
                Duration duracao = Duration.between(ultimaEntrada, ponto.getRegistro());
                totalHoras = totalHoras.plus(duracao);
                ultimaEntrada = null; // Preparando para a próxima entrada
            }
        }

        return totalHoras;
    }
}

