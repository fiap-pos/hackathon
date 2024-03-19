package br.com.fiap.hackathon.ponto.core.usecases.pedido;

import br.com.fiap.hackathon.ponto.core.domain.entities.Cliente;
import br.com.fiap.hackathon.ponto.core.domain.entities.ItemPedido;
import br.com.fiap.hackathon.ponto.core.domain.entities.Pedido;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;
import br.com.fiap.hackathon.ponto.core.dtos.CriaItemPedidoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.CriaPedidoDTO;
import br.com.fiap.hackathon.ponto.core.dtos.PedidoDTO;
import br.com.fiap.hackathon.ponto.core.ports.in.pedido.CriaPedidoInputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.cliente.BuscaClienteOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.pedido.CriaPedidoOutputPort;
import br.com.fiap.hackathon.ponto.core.ports.out.produto.BuscaProdutoPorIdOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CriaPedidoUseCase implements CriaPedidoInputPort {

    private final CriaPedidoOutputPort criaPedidoOutputPort;
    private final BuscaProdutoPorIdOutputPort buscaProdutoPorIdOutputPort;
    private final BuscaClienteOutputPort buscaClienteOutputPort;

    public CriaPedidoUseCase(CriaPedidoOutputPort criaPedidoOutputPort,
                             BuscaProdutoPorIdOutputPort buscaProdutoPorIdOutputPort,
                             BuscaClienteOutputPort buscaClienteOutputPort
    ) {
        this.criaPedidoOutputPort = criaPedidoOutputPort;
        this.buscaProdutoPorIdOutputPort = buscaProdutoPorIdOutputPort;
        this.buscaClienteOutputPort = buscaClienteOutputPort;
    }

    @Override
    public PedidoDTO criar(CriaPedidoDTO pedidoIn, String authorization) {

        var pedido = new Pedido(StatusPedidoEnum.PENDENTE_DE_PAGAMENTO);

        pedido.setCliente(getCliente(authorization));
        adicionaItemsPedido(pedido, pedidoIn.itens());

        return criaPedidoOutputPort.criar(new PedidoDTO(pedido));
    }

    private Cliente getCliente(String authorization) {
        var clienteDTO = buscaClienteOutputPort.buscarPorToken(authorization);
        return clienteDTO != null ? new Cliente(
                clienteDTO.id(),
                clienteDTO.nome(),
                clienteDTO.cpf(),
                clienteDTO.email()
        ) : null;
    }

    private void adicionaItemsPedido(Pedido pedido, List<CriaItemPedidoDTO> listaPedidosItem) {
        listaPedidosItem.forEach(itemPedidoIn -> {
            var produtoOut = buscaProdutoPorIdOutputPort.buscarPorId(itemPedidoIn.produtoId());
            pedido.addItemPedido(
                    new ItemPedido(produtoOut.id(), produtoOut.nome(), produtoOut.descricao(), produtoOut.preco(), itemPedidoIn.quantidade())
            );
        });
    }
}
