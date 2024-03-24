package br.com.fiap.hackathon.ponto.core.domain.entities;


import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TokenType;
import br.com.fiap.hackathon.ponto.core.domain.exceptions.AuthenticationTokenInvalidException;
import br.com.fiap.hackathon.ponto.core.dtos.AuthTokenDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.TokenInputport;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class AuthToken implements TokenInputport {

    private static final String ISSUER = "Hackathon Ponto";
    private static final Long TOKEN_DURATION_MINUTES = 5L;

    private final String secret;

    public AuthToken(String secret) {
        this.secret = secret;
    }


    public AuthTokenDTO criar(Usuario usuario) {
        return geraToken(usuario.getMatricula());
    }

    private AuthTokenDTO geraToken(String matricula) {

        var now = getCurrentDate();
        var expiresAt = now.plus(TOKEN_DURATION_MINUTES, ChronoUnit.MINUTES);

        var builder = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withClaim("matricula", matricula);

        var  accessToken = builder.sign(getAlgorithm());

        return new AuthTokenDTO(
                accessToken,
                TokenType.BEARER,
                getTokenDurationInSeconds()
        );
    }

    public String getMatricula(String token) {
        try {
            var verifier = JWT.require(getAlgorithm())
                    .withIssuer(ISSUER)
                    .build();
            var decodedJWT = verifier.verify(token.replace("Bearer ", ""));
            return getMatriculaFromJwt(decodedJWT);
        } catch (JWTVerificationException exception){
            throw new AuthenticationTokenInvalidException("Authorization token is inválid: " + exception.getMessage(), exception);
        }
    }

    private String getMatriculaFromJwt(DecodedJWT jwt) {
        return jwt.getClaim("matricula").asString();
    }

    private Instant getCurrentDate() {
        return ZonedDateTime.now(ZoneId.of("UTC")).toInstant();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    private Long getTokenDurationInSeconds() { return TOKEN_DURATION_MINUTES * 60L; }
}
