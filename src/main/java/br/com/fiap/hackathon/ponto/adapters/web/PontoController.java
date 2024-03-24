package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.PontoRequest;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.RelatorioPontoRequest;
import br.com.fiap.hackathon.ponto.adapters.web.models.responses.PontoResponse;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaUsuarioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.EnviaRelatorioInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.RegistraPontoInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final BuscaUsuarioInputPort buscaUsuarioInputPort;
    private final EnviaRelatorioInputPort enviaRelatorioInputPort;
    private final PontoMapper mapper;

    public PontoController(RegistraPontoInputPort registraPontoInputPort, BuscaStatusDiaInputPort buscaStatusDiaInputPort, BuscaUsuarioInputPort buscaUsuarioInputPort, EnviaRelatorioInputPort enviaRelatorioInputPort, PontoMapper mapper) {
        this.registraPontoInputPort = registraPontoInputPort;
        this.buscaStatusDiaInputPort = buscaStatusDiaInputPort;
        this.buscaUsuarioInputPort = buscaUsuarioInputPort;
        this.enviaRelatorioInputPort = enviaRelatorioInputPort;
        this.mapper = mapper;
    }

    @Operation(summary = "Busca status do dia por matricula")
    @GetMapping("/{matricula}")
    public ResponseEntity<List<PontoResponse>> buscarStatusDiaPorMatricula(@PathVariable("matricula") String matricula) {
        var pontosOut = buscaStatusDiaInputPort.buscaStatusDiaPorMatricula(matricula);
        var listPontoResponse = mapper.toPontoListResponse(pontosOut);
        return ResponseEntity.ok(listPontoResponse);
    }

    @Operation(summary = "Gerar relatorio")
    @PostMapping("/relatorio")
    public ResponseEntity<String> gerarRelatorio(@RequestBody RelatorioPontoRequest relatorioPontoRequest) {
        var usuario = buscaUsuarioInputPort.buscaUsuarioPorMatricula(relatorioPontoRequest.getMatricula());
        var relatorioPontoIn = mapper.toRelatorioPontoDTO(relatorioPontoRequest, usuario.email());
        var relatorioPontoOut = enviaRelatorioInputPort.enviaRelatorioPontoParaFilaRelatorios(relatorioPontoIn);
        return ResponseEntity.ok(relatorioPontoOut);
    }

    @Operation(summary = "Registrar ponto")
    @PostMapping
    public ResponseEntity<PontoResponse> registrarPonto(@RequestBody PontoRequest pontoRequest) {
        var pontoIn = mapper.toPontoDTO(pontoRequest);
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
