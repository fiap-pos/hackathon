package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.PontoRequest;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.RelatorioPontoRequest;
import br.com.fiap.hackathon.ponto.adapters.web.models.responses.PontoResponse;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Ponto", description = "API para gerenciamento de registro de ponto")
@RestController
@RequestMapping("/ponto")
@RequiredArgsConstructor
public class PontoController {

    private final RegistraPontoInputPort registraPontoInputPort;
    private final BuscaStatusDiaInputPort buscaStatusDiaInputPort;
    private final BuscaUsuarioInputPort buscaUsuarioInputPort;
    private final EnviaRelatorioInputPort enviaRelatorioInputPort;
    private final PontoMapper mapper;
    private final TokenInputport tokenInputport;

    @Operation(summary = "Busca status do dia")
    @GetMapping("")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<PontoResponse>> buscarStatusDiaPorMatricula(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token) {
        var matricula = tokenInputport.getMatricula(token);
        var pontosOut = buscaStatusDiaInputPort.buscaStatusDiaPorMatricula(matricula);
        var listPontoResponse = mapper.toPontoListResponse(pontosOut);
        return ResponseEntity.ok(listPontoResponse);
    }

    @Operation(summary = "Gerar relatorio")
    @PostMapping("/relatorio")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> gerarRelatorio(
            @Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
            @RequestBody RelatorioPontoRequest relatorioPontoRequest
    ) {
        var matricula = tokenInputport.getMatricula(token);
        var usuario = buscaUsuarioInputPort.buscaUsuarioPorMatricula(matricula);
        var relatorioPontoIn = mapper.toRelatorioPontoDTO(relatorioPontoRequest, usuario.email(), matricula);
        var relatorioPontoOut = enviaRelatorioInputPort.enviaRelatorioPontoParaFilaRelatorios(relatorioPontoIn);
        return ResponseEntity.ok(relatorioPontoOut);
    }

    @Operation(summary = "Registrar ponto")
    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<PontoResponse> registrarPonto(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token) {
        var matricula = tokenInputport.getMatricula(token);
        var pontoIn = mapper.toPontoDTO(new PontoRequest(matricula));
        var pontoOut = registraPontoInputPort.registrar(pontoIn);
        var pontoResponseOut = mapper.toPontoResponse(pontoOut);
        return ResponseEntity.created(getExpandedCurrentUri(pontoResponseOut.getId())).body(pontoResponseOut);
    }

    private URI getExpandedCurrentUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
