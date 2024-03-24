package br.com.fiap.hackathon.ponto.adapters.web.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class PontoDiaResponse {

    private List<PontoResponse> registros;

    private String totalHorasDia;

}
