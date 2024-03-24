package br.com.fiap.hackathon.ponto.core.domain.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UnauthorizedException(Throwable throwable) {
        super(throwable);
    }

}
