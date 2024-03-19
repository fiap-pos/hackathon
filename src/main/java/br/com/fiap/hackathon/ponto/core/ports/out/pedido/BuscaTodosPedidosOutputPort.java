package br.com.fiap.hackathon.ponto.core.ports.out.pedido;

import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;
import java.util.List;

public interface BuscaTodosPedidosOutputPort {
    List<PedidoDTO> buscarTodos();
}
