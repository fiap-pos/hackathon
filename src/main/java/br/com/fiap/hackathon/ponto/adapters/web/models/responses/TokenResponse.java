package br.com.fiap.hackathon.ponto.adapters.web.models.responses;

import br.com.fiap.hackathon.ponto.core.dtos.AuthTokenDTO;

public class TokenResponse {

    private String type;
    private String accessToken;
    private Long expiresIn;

    public TokenResponse(AuthTokenDTO authTokenDTO) {
        this.type = authTokenDTO.tokenType().name().toLowerCase();
        this.accessToken = authTokenDTO.accessToken();
        this.expiresIn = authTokenDTO.expiresIn();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
