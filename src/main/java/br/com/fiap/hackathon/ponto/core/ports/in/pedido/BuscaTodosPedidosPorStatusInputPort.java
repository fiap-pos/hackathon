package br.com.fiap.hackathon.ponto.core.ports.in.pedido;


import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;

import java.util.List;

public interface BuscaTodosPedidosPorStatusInputPort {
    List<PedidoDTO> buscarTodosStatus(StatusPedidoEnum status);
}
