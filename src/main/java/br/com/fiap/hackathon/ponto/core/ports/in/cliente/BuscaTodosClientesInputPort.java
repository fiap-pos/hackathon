package br.com.fiap.hackathon.ponto.core.ports.in.cliente;

import br.com.fiap.hackathon.ponto.core.dtos.ClienteDTO;
import java.util.List;

public interface BuscaTodosClientesInputPort {
    List<ClienteDTO> buscarTodos();
}
