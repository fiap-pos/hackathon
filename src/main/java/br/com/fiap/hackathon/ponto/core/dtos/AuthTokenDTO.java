package br.com.fiap.hackathon.ponto.core.dtos;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TokenType;

public record AuthTokenDTO(String accessToken, TokenType tokenType, Long expiresIn) {
}
