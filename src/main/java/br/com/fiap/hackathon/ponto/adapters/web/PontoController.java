package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.PontoRequest;
import br.com.fiap.hackathon.ponto.adapters.web.models.responses.PontoResponse;
import br.com.fiap.hackathon.ponto.core.ports.in.RegistraPontoInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@Tag(name = "Ponto", description = "APIs para gerenciamento de registro de ponto")
@RestController
@RequestMapping("/ponto")
public class PontoController {

    private final RegistraPontoInputPort registraPontoInputPort;
    private final PontoMapper mapper;

    public PontoController(RegistraPontoInputPort registraPontoInputPort, PontoMapper mapper) {
        this.registraPontoInputPort = registraPontoInputPort;
        this.mapper = mapper;
    }


    private URI getExpandedCurrentUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
