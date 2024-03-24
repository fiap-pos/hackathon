package br.com.fiap.hackathon.ponto.core.domain.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum TipoPontoEnum {
    ENTRADA("Entrada"),
    SAIDA("Saída");

    private final String descricao;

    TipoPontoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoPontoEnum fromString(String value) {
        return Stream.of(values())
                .filter(tipo -> tipo.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipos de registros permitidos: " + Stream.of(values()).toList()));
    }
}
