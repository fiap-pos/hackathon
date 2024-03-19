package br.com.fiap.hackathon.ponto.utils;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.TipoRegistroEnum;
import br.com.fiap.hackathon.ponto.core.dtos.PontoDTO;

import java.time.LocalDateTime;

public abstract class PontoHelper {

    private static final Long ID = 1L;
    private static final String MATRICULA = "12345";
    private static final LocalDateTime REGISTRO = LocalDateTime.now();
    private static final TipoRegistroEnum TIPO_REGISTRO = TipoRegistroEnum.ENTRADA;

    public static PontoDTO getPontoDTO() {
        return new PontoDTO(ID, MATRICULA, REGISTRO, TIPO_REGISTRO);
    }
    public static Ponto getPonto() {
        return new Ponto( MATRICULA, REGISTRO, TIPO_REGISTRO);
    }
}
