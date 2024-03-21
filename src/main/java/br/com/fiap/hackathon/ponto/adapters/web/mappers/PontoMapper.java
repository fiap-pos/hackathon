package br.com.fiap.hackathon.ponto.adapters.web.mappers;

import br.com.fiap.hackathon.ponto.adapters.web.models.requests.PontoRequest;
import br.com.fiap.hackathon.ponto.adapters.web.models.responses.PontoResponse;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component("pontoMapperWeb")
public class PontoMapper {

    public PontoResponse toPontoResponse(PontoDTO ponto){
        return new PontoResponse(
                ponto.id(),
                ponto.matricula(),
                ponto.horaRegistro(),
                ponto.tipoRegistro()
        );
    }

    public List<PontoResponse> toPontoListResponse(List<PontoDTO> pontosOut){
        List<PontoResponse> pontosResponse = new ArrayList<>();
        pontosOut.forEach(pontoOut -> pontosResponse.add(toPontoResponse(pontoOut)));
        return pontosResponse;
    }

    public PontoDTO toPontoDTO(PontoRequest pontoRequest){
        return new PontoDTO(
                pontoRequest.getMatricula(),
                LocalDateTime.now(),
                null
        );
    }
}
