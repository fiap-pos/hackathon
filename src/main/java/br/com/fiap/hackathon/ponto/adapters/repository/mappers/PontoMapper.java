package br.com.fiap.hackathon.ponto.adapters.repository.mappers;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public List<PontoDTO> toPontoDTOList(List<Ponto> pontoList){
        return pontoList.stream().map(this::toPontoDTO).toList();
    }
}
