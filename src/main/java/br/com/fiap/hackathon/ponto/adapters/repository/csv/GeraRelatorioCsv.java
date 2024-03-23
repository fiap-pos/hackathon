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
        LocalDateTime fim = inicio.withDayOfMonth(inicio.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59);

        String nomeRelatorioCSV = nomeArquivo.replace("{matricula}", matricula)
                .replace("{mes}", String.format("%02d", mes))
                .replace("{ano}", String.valueOf(ano));

        return geraRelatorioCSV(matricula, inicio, fim, nomeRelatorioCSV);
    }

    public String geraRelatorioCSV(String matricula, LocalDateTime inicio, LocalDateTime fim, String nomeRelatorioCSV) {
        List<Ponto> registros = pontoJpaRepository.findByMatriculaAndRegistroBetween(matricula, inicio, fim);

        Map<LocalDate, List<Ponto>> registrosPorDia = registros.stream()
                .collect(Collectors.groupingBy(p -> p.getRegistro().toLocalDate(), LinkedHashMap::new, Collectors.toList()));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (CSVWriter writer = new CSVWriter(new FileWriter(nomeRelatorioCSV))) {
            List<String> headerList = new ArrayList<>();
            headerList.add("Dia");

            // Calculando a quantidade máxima de colunas para Entradas e Saídas
            int maxColunasEntradas = registrosPorDia.values().stream()
                    .mapToInt(pontosDia -> (int) pontosDia.stream().filter(p -> p.getTipoRegistro() == TipoRegistroEnum.ENTRADA).count())
                    .max().orElse(0);

            int maxColunasSaidas = registrosPorDia.values().stream()
                    .mapToInt(pontosDia -> (int) pontosDia.stream().filter(p -> p.getTipoRegistro() == TipoRegistroEnum.SAIDA).count())
                    .max().orElse(0);

            // A quantidade de colunas de "Entrada" e "Saída" deve se basear no maior valor entre o número máximo de entradas e saídas
            int maxColunas = Math.max(maxColunasEntradas, maxColunasSaidas);

            for (int i = 0; i < maxColunas; i++) {
                headerList.add("Entrada");
                headerList.add("Saída");
            }
            headerList.add("Horas Trabalhadas");
            writer.writeNext(headerList.toArray(new String[0]));

            registrosPorDia.forEach((dia, pontosDia) -> {
                List<String> linha = new ArrayList<>();
                linha.add(dia.toString());

                Duration totalHoras = Duration.ZERO;
                LocalDateTime ultimaEntrada = null;

                for (Ponto ponto : pontosDia) {
                    LocalDateTime horario = ponto.getRegistro();
                    if (ponto.getTipoRegistro() == TipoRegistroEnum.ENTRADA) {
                        ultimaEntrada = horario;
                    } else if (ultimaEntrada != null && ponto.getTipoRegistro() == TipoRegistroEnum.SAIDA) {
                        Duration duracao = Duration.between(ultimaEntrada, horario);
                        totalHoras = totalHoras.plus(duracao);
                        ultimaEntrada = null;
                    }
                    linha.add(horario.format(timeFormatter)); // Este adicionará Entradas e Saídas mesmo sem par correspondente
                }

                // Preenchendo com vazios caso não haja registro suficiente para preencher todas as colunas
                while (linha.size() < headerList.size() - 1) { // -1 pois a última coluna é para o total de horas
                    linha.add("");
                }

                // Adicionando o total de horas trabalhadas no final
                long horas = totalHoras.toHours();
                long minutos = totalHoras.toMinutesPart();
                linha.add(String.format("%02d:%02d", horas, minutos));

                writer.writeNext(linha.toArray(new String[0]));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nomeRelatorioCSV;
    }
}