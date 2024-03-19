package br.com.fiap.hackathon.ponto.core.ports.in.pedido;

import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;

public interface BuscarPedidoPorIdInputPort {
    PedidoDTO buscarPorId(Long id);
}
