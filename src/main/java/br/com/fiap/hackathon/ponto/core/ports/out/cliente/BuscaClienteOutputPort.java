package br.com.fiap.hackathon.ponto.core.ports.out.cliente;

import br.com.fiap.hackathon.ponto.core.dtos.ClienteDTO;

public interface BuscaClienteOutputPort {
    ClienteDTO buscarPorId(String id);

    ClienteDTO buscarPorToken(String authorization);
}
