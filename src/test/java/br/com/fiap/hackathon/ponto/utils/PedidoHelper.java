//package br.com.fiap.hackathon.ponto.utils;
//
//import br.com.fiap.hackathon.ponto.adapters.repository.models.Pedido;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//
//import static br.com.fiap.hackathon.ponto.utils.ClienteHelper.getClienteDTO;
//import static br.com.fiap.hackathon.ponto.utils.ProdutoHelper.getProduto;
//
//public abstract class PedidoHelper {
//    private static final Long PEDIDO_ID = 1L;
//    private static final ClienteDTO CLIENTE_DTO = getClienteDTO();
//    private static final List<ItemPedidoDTO> ITENS = List.of(getItemPedidoDTO());
//    private static final StatusPedidoEnum PEDIDO_STATUS = StatusPedidoEnum.PENDENTE_DE_PAGAMENTO;
//    private static final LocalDateTime DATA_CRIACAO = LocalDateTime.parse("2024-01-08T20:31:51.620293057");
//
//    private static final Long PRODUTO_ID = 1L;
//    private static final String PRODUTO_NOME = "X TUDO";
//    private static final String PRODUTO_DESCRICAO = "X Tudo mostro com hamburger, salcicha, bacon, ovo, salada, queijo";
//
//    public static PedidoDTO getPedidoDTO() {
//        return new PedidoDTO(PEDIDO_ID, CLIENTE_DTO, ITENS, PEDIDO_STATUS, BigDecimal.valueOf(106.8), DATA_CRIACAO);
//    }
//
//    public static PedidoDTO getPedidoDTOStatusPago() {
//        return new PedidoDTO(PEDIDO_ID, CLIENTE_DTO, ITENS, StatusPedidoEnum.PAGO, BigDecimal.valueOf(106.8), DATA_CRIACAO);
//    }
//
//    public static PedidoDTO getPedidoDTOSemCliente() {
//        return new PedidoDTO(PEDIDO_ID, null, ITENS, PEDIDO_STATUS, BigDecimal.valueOf(106.8), DATA_CRIACAO);
//    }
//
//    public static PedidoDTO getPedidoDTOSemClienteComStatus(StatusPedidoEnum status) {
//        return new PedidoDTO(PEDIDO_ID, null, ITENS, status, BigDecimal.valueOf(106.8), DATA_CRIACAO);
//    }
//
//    public static PedidoDTO getPedidoDTOcomStatus(StatusPedidoEnum status) {
//        return new PedidoDTO(PEDIDO_ID, CLIENTE_DTO, ITENS, status, BigDecimal.valueOf(106.8), DATA_CRIACAO);
//    }
//
//    public static ItemPedidoDTO getItemPedidoDTO() {
//        return new ItemPedidoDTO(1L, PRODUTO_NOME, PRODUTO_DESCRICAO, BigDecimal.valueOf(45.9), 2);
//    }
//
//    public static List<PedidoDTO> getListaPedidoDTO() {
//        return List.of(getPedidoDTO());
//    }
//
//
//    public static Pedido getPedido() {
//        var pedido = new Pedido();
//        pedido.setId(PEDIDO_ID);
//        pedido.setStatus(PEDIDO_STATUS);
//        pedido.setValorTotal(BigDecimal.valueOf(106.8));
//        pedido.setData(DATA_CRIACAO);
//        pedido.setClienteId(CLIENTE_DTO.id());
//        return pedido;
//    }
//
//    public static Pedido getPedidoSemCliente() {
//        var pedido = new Pedido();
//        pedido.setId(PEDIDO_ID);
//        pedido.setStatus(PEDIDO_STATUS);
//        pedido.setValorTotal(BigDecimal.valueOf(106.8));
//        pedido.setData(DATA_CRIACAO);
//        return pedido;
//    }
//
//    public static ItemPedido getItemPedido(Pedido pedido) {
//        return new ItemPedido(pedido, getProduto(), 2, BigDecimal.valueOf(45.9));
//    }
//
//    public static CriaPedidoDTO getCriaPedidoDTO() {
//        return new CriaPedidoDTO(Collections.singletonList(getCriaItemPedidoDTO()));
//    }
//
//    public static CriaItemPedidoDTO getCriaItemPedidoDTO() {
//        return new CriaItemPedidoDTO(1L, 2);
//    }
//
//    public static AtualizaStatusPedidoDTO getAtualizaStatusPedidoDTO(StatusPedidoEnum status) {
//        return new AtualizaStatusPedidoDTO(status);
//    }
//}
