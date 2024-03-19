package br.com.fiap.hackathon.ponto.core.ports.in.pedido;

import br.com.fiap.hackathon.ponto.core.dtos.CriaPedidoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;

public interface CriaPedidoInputPort {
    PedidoDTO criar(CriaPedidoDTO pedidoIn, String authorization);
}
