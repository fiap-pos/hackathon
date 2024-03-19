package br.com.fiap.hackathon.ponto.core.ports.in.cliente;

import br.com.fiap.hackathon.ponto.core.dtos.ClienteDTO;

public interface CadastraClienteInputPort {
    ClienteDTO cadastrar(ClienteDTO cliente);
}
