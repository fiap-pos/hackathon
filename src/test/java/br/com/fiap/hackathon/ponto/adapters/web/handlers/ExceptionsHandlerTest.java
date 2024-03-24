package br.com.fiap.hackathon.ponto.adapters.web.handlers;

import br.com.fiap.hackathon.ponto.core.domain.exceptions.BadRequestException;
import br.com.fiap.hackathon.ponto.core.domain.exceptions.EntityAlreadyExistException;
import br.com.fiap.hackathon.ponto.core.domain.exceptions.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletRequest;

import java.net.ConnectException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class ExceptionsHandlerTest {

    private ExceptionsHandler exceptionsHandler;

    @BeforeEach
    void setUp() {
        exceptionsHandler = new ExceptionsHandler();
    }

    @Test
    void shouldHandleException() {
        var exception = new RuntimeException("message");
        var httpservletRequest = new MockHttpServletRequest();

        var response = exceptionsHandler.handlerException(exception, httpservletRequest);

        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response.getBody()).isNotNull().isInstanceOf(ErrorDetails.class);
        assertThat(response.getBody().message()).isEqualTo(exception.getMessage());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Test
    void shouldHandleBadRequestException() {
        var exception = new BadRequestException("message");
        var httpservletRequest = new MockHttpServletRequest();

        var response = exceptionsHandler.handlerBadRequestException(exception, httpservletRequest);

        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response.getBody()).isNotNull().isInstanceOf(ErrorDetails.class);
        assertThat(response.getBody().message()).isEqualTo(exception.getMessage());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldHandleEntityAlreadyExistException() {
        var exception = new EntityAlreadyExistException("message");
        var httpservletRequest = new MockHttpServletRequest();

        var response = exceptionsHandler.handlerEntityAlreadyExistException(exception, httpservletRequest);

        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response.getBody()).isNotNull().isInstanceOf(ErrorDetails.class);
        assertThat(response.getBody().message()).isEqualTo(exception.getMessage());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void shouldHandleEntityNotFoundException() {
        var exception = new EntityNotFoundException("message");
        var httpservletRequest = new MockHttpServletRequest();

        var response = exceptionsHandler.handlerEntityNotFoundException(exception, httpservletRequest);

        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response.getBody()).isNotNull().isInstanceOf(ErrorDetails.class);
        assertThat(response.getBody().message()).isEqualTo(exception.getMessage());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldHandleHttpMessageNotReadableException() {
        var exceptionCause = new RuntimeException("Requisição inválida.");
        var exception = new HttpMessageNotReadableException(
                "Error", exceptionCause, mock(HttpInputMessage.class)
        );
        var httpservletRequest = new MockHttpServletRequest();

        var response = exceptionsHandler.handlerHttpMessageNotReadableException(exception, httpservletRequest);

        assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        assertThat(response.getBody()).isNotNull().isInstanceOf(ErrorDetails.class);
        assertThat(response.getBody().message()).isEqualTo("Requisição inválida.");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void shouldHandleIllegalArgumentException() {
        var exception = new IllegalArgumentException("Erro");
        var httpservletRequest = new MockHttpServletRequest();
        var response = exceptionsHandler.handlerIllegalArgumentException(exception, httpservletRequest);

        Assertions.assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(response.getBody()).isNotNull().isInstanceOf(ErrorDetails.class);
        Assertions.assertThat(response.getBody().message()).isEqualTo(exception.getMessage());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @Test
    void shouldHandleRuntimeException() {
        var exception = new ConnectException("Serviço Indisponível");
        var httpservletRequest = new MockHttpServletRequest();
        var response = exceptionsHandler.handlerRuntimeException(exception, httpservletRequest);

        Assertions.assertThat(response).isNotNull().isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(response.getBody()).isNotNull().isInstanceOf(ErrorDetails.class);
        Assertions.assertThat(response.getBody().message()).isEqualTo(exception.getMessage());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }
}