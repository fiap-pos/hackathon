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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Repository
@RequiredArgsConstructor
public class GeraRelatorioCsv implements GeraRelatorioOutputPort {

    private final PontoJpaRepository pontoJpaRepository;

    @Value("${relatorio.csv}")
    private String nomeArquivo;

    @Override
    public String geraRelatorio(String matricula, int mes, int ano) {
        var inicio = LocalDateTime.of(ano, mes, 1, 0, 0);
        var fim = LocalDateTime.of(ano, mes, inicio.with(lastDayOfMonth()).getDayOfMonth(), 23, 59);
        var nomeRelatorioCSV = nomeArquivo.replace("{matricula}", matricula)
                .replace("{mes}", String.valueOf(mes))
                .replace("{ano}", String.valueOf(ano));
        return geraRelatorioCSV(matricula, inicio, fim, nomeRelatorioCSV);
    }

    public String geraRelatorioCSV(String matricula, LocalDateTime inicio, LocalDateTime fim, String nomeRelatorioCSV) {
        List<Ponto> registros = pontoJpaRepository.findByMatricula(matricula, inicio, fim);

        Map<LocalDate, List<Ponto>> registrosPorDia = registros.stream()
                .collect(Collectors.groupingBy(p -> p.getRegistro().toLocalDate(), LinkedHashMap::new, Collectors.toList()));

        int maxRegistrosPorTipo = registrosPorDia.values().stream()
                .flatMapToInt(dayList -> Map.of(
                        TipoRegistroEnum.ENTRADA, (int) dayList.stream().filter(p -> p.getTipoRegistro() == TipoRegistroEnum.ENTRADA).count(),
                        TipoRegistroEnum.SAIDA, (int) dayList.stream().filter(p -> p.getTipoRegistro() == TipoRegistroEnum.SAIDA).count()
                ).values().stream().mapToInt(Integer::intValue))
                .max().orElse(0);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (CSVWriter writer = new CSVWriter(new FileWriter(nomeRelatorioCSV))) {
            List<String> headerList = new ArrayList<>();
            headerList.add("Dia");
            for (int i = 0; i < maxRegistrosPorTipo; i++) {
                headerList.add("Entrada");
                headerList.add("Saída");
            }
            writer.writeNext(headerList.toArray(new String[0]));

            registrosPorDia.forEach((dia, pontosDia) -> {
                List<String> linha = new ArrayList<>();
                linha.add(dia.toString());

                Map<TipoRegistroEnum, List<Ponto>> agrupadosPorTipo = pontosDia.stream()
                        .collect(Collectors.groupingBy(Ponto::getTipoRegistro, LinkedHashMap::new, Collectors.toList()));

                for (int i = 0; i < maxRegistrosPorTipo; i++) {
                    for (TipoRegistroEnum tipo : TipoRegistroEnum.values()) {
                        List<Ponto> pontosTipo = agrupadosPorTipo.getOrDefault(tipo, new ArrayList<>());
                        if (i < pontosTipo.size()) {
                            LocalDateTime horario = pontosTipo.get(i).getRegistro();
                            linha.add(horario.format(timeFormatter)); // Formatando a hora
                        } else {
                            linha.add("");
                        }
                    }
                }

                writer.writeNext(linha.toArray(new String[0]));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nomeRelatorioCSV;
    }


}
