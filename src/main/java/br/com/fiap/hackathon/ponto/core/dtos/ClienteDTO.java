package br.com.fiap.hackathon.ponto.core.dtos;

import br.com.fiap.hackathon.ponto.core.domain.entities.Cliente;

public record ClienteDTO(String id, String nome, String cpf, String email) {

    public ClienteDTO(String nome, String cpf, String email) {
        this(null, nome, cpf, email);
    }

    public ClienteDTO(String id) {
        this(id, null, null, null);
    }

    public ClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
    }
}
