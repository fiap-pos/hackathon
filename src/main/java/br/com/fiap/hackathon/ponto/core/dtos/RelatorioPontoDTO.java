package br.com.fiap.hackathon.ponto.core.dtos;

public record RelatorioPontoDTO(String matricula, int mes, int ano, String email, String nomeArquivoRelatorio) {
    public RelatorioPontoDTO {
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula não pode ser nula ou vazia");
        }
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês deve ser um valor entre 1 e 12");
        }
        if (ano < 1900 || ano > 2100) {
            throw new IllegalArgumentException("Ano deve ser um valor entre 1900 e 2100");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
    }
}
