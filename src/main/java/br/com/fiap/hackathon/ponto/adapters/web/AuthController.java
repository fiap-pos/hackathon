package br.com.fiap.hackathon.ponto.adapters.web;

import br.com.fiap.hackathon.ponto.adapters.web.models.requests.AutenticaUsuarioRequest;
import br.com.fiap.hackathon.ponto.core.dtos.AutenticaUsuarioDTO;
import br.com.fiap.hackathon.ponto.core.dtos.AuthTokenDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.AutenticaUsuarioInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "API para gerenciamento do cadastro dos usuarios")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AutenticaUsuarioInputPort autenticaUsuarioInputPort;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Autentica um usuario")
    @PostMapping("/login")
    public ResponseEntity<AuthTokenDTO> autenticaUsuario(@RequestBody AutenticaUsuarioRequest request) {
        var autenticaUsuarioDTO = new AutenticaUsuarioDTO(request.getMatricula(), request.getSenha());
        var tokenDTO = autenticaUsuarioInputPort.autenticaUsuario(autenticaUsuarioDTO);
        return ResponseEntity.ok(tokenDTO);
    }
}
