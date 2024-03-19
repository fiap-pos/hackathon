package br.com.fiap.hackathon.ponto.core.ports.in.pedido;

import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;

public interface AtualizaStatusPedidoInputPort {
    PedidoDTO atualizarStatus(Long id, StatusPedidoEnum pedidoStatusIn);
}
