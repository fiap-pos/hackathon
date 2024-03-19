package br.com.fiap.hackathon.ponto.core.ports.out.cliente;

import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;

public interface NotificaClienteOuputPort {
    void notificaClienteStatusPedido(PedidoDTO pedidoDTO);
}
