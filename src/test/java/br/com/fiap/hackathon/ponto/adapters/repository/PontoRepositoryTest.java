package br.com.fiap.hackathon.ponto.adapters.repository;

import br.com.fiap.hackathon.ponto.adapters.repository.jpa.PontoJpaRepository;
import br.com.fiap.hackathon.ponto.adapters.repository.mappers.PontoMapper;
import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPonto;
import static br.com.fiap.hackathon.ponto.utils.PontoHelper.getPontoDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PontoRepositoryTest {

    private PontoRepository repository;

    @Mock
    PontoMapper mapper;

    @Mock
    PontoJpaRepository jpaRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        repository = new PontoRepository(jpaRepository, mapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void buscarStatusDia(){
            var ponto = getPonto();
            var listaPontos = List.of(ponto);
            var pontoDTOList = List.of(getPontoDTO());

            when(jpaRepository.findByMatriculaAndRegistroBetween(getPontoDTO().matricula(), LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX))).thenReturn(listaPontos);
            when(mapper.toPontoDTOList(listaPontos)).thenReturn(pontoDTOList);

            var listaPontosEncontrados = repository.buscaStatusDiaPorMatricula(getPontoDTO().matricula());

            assertThat(listaPontosEncontrados).isNotNull();

            verify(jpaRepository, times(1)).findByMatriculaAndRegistroBetween(getPontoDTO().matricula(), LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
            verify(mapper, times(1)).toPontoDTOList(any());
    }
}
