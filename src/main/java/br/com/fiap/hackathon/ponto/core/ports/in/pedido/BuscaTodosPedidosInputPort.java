package br.com.fiap.hackathon.ponto.core.ports.in.pedido;


import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;
import java.util.List;

public interface BuscaTodosPedidosInputPort {
    List<PedidoDTO> buscarTodos();
}
