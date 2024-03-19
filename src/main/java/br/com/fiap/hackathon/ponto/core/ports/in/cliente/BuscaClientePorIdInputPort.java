package br.com.fiap.hackathon.ponto.core.ports.in.cliente;

import br.com.fiap.hackathon.ponto.core.dtos.ClienteDTO;

public interface BuscaClientePorIdInputPort {
    ClienteDTO buscar(String cpf);

    ClienteDTO buscar(Long id);
}
