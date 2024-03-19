package br.com.fiap.hackathon.ponto.core.dtos;

import java.util.List;

public record CriaPedidoDTO(
    List<CriaItemPedidoDTO> itens
)  {
}
