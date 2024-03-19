package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.web.models.responses.PontoResponse;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.RegistraPontoInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Ponto", description = "API para gerenciamento de registro de ponto")
@RestController
@RequestMapping("/ponto")
public class PontoController {

    private final RegistraPontoInputPort registraPontoInputPort;
    private final BuscaStatusDiaInputPort buscaStatusDiaInputPort;
    private final PontoMapper mapper;

    public PontoController(RegistraPontoInputPort registraPontoInputPort, BuscaStatusDiaInputPort buscaStatusDiaInputPort, PontoMapper mapper) {
        this.registraPontoInputPort = registraPontoInputPort;
        this.buscaStatusDiaInputPort = buscaStatusDiaInputPort;
        this.mapper = mapper;
    }

    @Operation(summary = "Busca status do dia")
    @GetMapping
    public ResponseEntity<List<PontoResponse>> buscarStatusDia(){
        var pontosOut = buscaStatusDiaInputPort.buscaStatusDia();
        var listPontoResponse = mapper.toPontoListResponse(pontosOut);
        return ResponseEntity.ok(listPontoResponse);
    }

    private URI getExpandedCurrentUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
