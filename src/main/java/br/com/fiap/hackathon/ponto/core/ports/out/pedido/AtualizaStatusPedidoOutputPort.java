package br.com.fiap.hackathon.ponto.core.ports.out.pedido;

import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;

public interface AtualizaStatusPedidoOutputPort {
    PedidoDTO atualizarStatus(Long id, StatusPedidoEnum status);
}
