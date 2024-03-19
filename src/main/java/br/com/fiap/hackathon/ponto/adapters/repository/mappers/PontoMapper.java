package br.com.fiap.hackathon.ponto.adapters.repository.mappers;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import org.springframework.stereotype.Component;

@Component
public class PontoMapper {
//    private final AuthGateway authGateway;

//    public PontoMapper(AuthGateway authGateway) {
//        this.authGateway = authGateway;
//    }

    public Ponto toPonto(PontoDTO pontoIn){
        // TODO: pegar matrícula com authgateway
        return new Ponto(pontoIn.matricula(), pontoIn.horaRegistro(), pontoIn.tipoRegistro());

    }

    public PontoDTO toPontoDTO(Ponto ponto){
        return new PontoDTO(ponto.getMatricula(), ponto.getRegistro(), ponto.getTipoRegistro());
    }
}
