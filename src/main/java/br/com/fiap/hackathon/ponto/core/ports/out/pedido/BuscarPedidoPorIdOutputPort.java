package br.com.fiap.hackathon.ponto.core.ports.out.pedido;

import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;

public interface BuscarPedidoPorIdOutputPort {

    PedidoDTO buscarPorId(Long id);
}
