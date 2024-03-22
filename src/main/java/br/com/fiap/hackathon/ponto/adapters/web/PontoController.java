package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.web.models.requests.PontoRequest;
import br.com.fiap.hackathon.ponto.adapters.web.models.responses.PontoResponse;
import br.com.fiap.hackathon.ponto.core.ports.in.BuscaStatusDiaInputPort;
import br.com.fiap.hackathon.ponto.core.ports.in.GeraRelatorioInputPort;
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
@RequiredArgsConstructor
public class PontoController {

    private final RegistraPontoInputPort registraPontoInputPort;
    private final BuscaStatusDiaInputPort buscaStatusDiaInputPort;
    private final GeraRelatorioInputPort geraRelatorioInputPort;
    private final PontoMapper mapper;

    @Operation(summary = "Busca status do dia")
    @GetMapping
    public ResponseEntity<List<PontoResponse>> buscarStatusDia() {
        var pontosOut = buscaStatusDiaInputPort.buscaStatusDia();
        var listPontoResponse = mapper.toPontoListResponse(pontosOut);
        return ResponseEntity.ok(listPontoResponse);
    }

    @Operation(summary = "Busca status do dia por matricula")
    @GetMapping("/{matricula}")
    public ResponseEntity<List<PontoResponse>> buscarStatusDia(@PathVariable("matricula") String matricula) {
        var pontosOut = buscaStatusDiaInputPort.buscaStatusDiaPorMatricula(matricula);
        var listPontoResponse = mapper.toPontoListResponse(pontosOut);
        return ResponseEntity.ok(listPontoResponse);
    }

    @Operation(summary = "Gerar relatorio")
    @PostMapping("/relatorio")
    public ResponseEntity<String> gerarRelatorio(@RequestBody PontoRequest pontoRequest) {
        var mensagem = geraRelatorioInputPort.geraRelatorio(pontoRequest.getMatricula(), pontoRequest.getMes(), pontoRequest.getAno());
        return ResponseEntity.ok(mensagem);
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
