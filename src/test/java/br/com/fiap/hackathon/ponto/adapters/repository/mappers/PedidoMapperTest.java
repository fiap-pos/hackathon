//package br.com.fiap.hackathon.ponto.adapters.repository.mappers;
//
//import br.com.fiap.hackathon.ponto.adapters.gateways.auth.AuthGateway;
//import br.com.fiap.hackathon.ponto.adapters.repository.models.Pedido;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static br.com.fiap.hackathon.ponto.utils.ClienteHelper.getClienteDTO;
//import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getItemPedido;
//import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getPedido;
//import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getPedidoDTO;
//import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getPedidoDTOSemCliente;
//import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getPedidoSemCliente;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class PedidoMapperTest {
//
//    @Mock
//    ItemPedidoMapper itemPedidoMapper;
//
//    @Mock
//    AuthGateway authGateway;
//
//    private PedidoMapper pedidoMapper;
//
//    AutoCloseable mock;
//
//    @BeforeEach
//    void setUp() {
//        mock = MockitoAnnotations.openMocks(this);
//        this.pedidoMapper = new PedidoMapper(itemPedidoMapper, authGateway);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        mock.close();
//    }
//
//    @Test
//    void toPedido() {
//        var pedidoDTO = getPedidoDTO();
//        var clienteDTO = getClienteDTO();
//        var pedido = getPedido();
//        var listaItemPedido = List.of(getItemPedido(pedido));
//        var listaItemPedidoDTO = pedidoDTO.itens();
//
//        when(authGateway.buscarPorId(pedidoDTO.cliente().id())).thenReturn(clienteDTO);
//        when(itemPedidoMapper.toItemPedido(pedido, listaItemPedidoDTO)).thenReturn(listaItemPedido);
//
//        Pedido mapperPedido = pedidoMapper.toPedido(pedidoDTO);
//        assertThat(mapperPedido).isNotNull();
//
//        verify(authGateway, times(1)).buscarPorId(pedidoDTO.cliente().id());
//    }
//
//    @Test
//    void toPedido_SemCliente() {
//        var pedidoDTO = getPedidoDTOSemCliente();
//        var pedido = getPedido();
//        var listaItemPedido = List.of(getItemPedido(pedido));
//        var listaItemPedidoDTO = pedidoDTO.itens();
//
//        when(itemPedidoMapper.toItemPedido(pedido, listaItemPedidoDTO)).thenReturn(listaItemPedido);
//
//        Pedido mapperPedido = pedidoMapper.toPedido(pedidoDTO);
//        assertThat(mapperPedido).isNotNull();
//
//    }
//
//    @Test
//    void toPedidoDTO() {
//        var pedidoDTO = getPedidoDTO();
//        var pedido = getPedido();
//        var listaItemPedido = List.of(getItemPedido(pedido));
//        var listaItemPedidoDTO = pedidoDTO.itens();
//
//        when(itemPedidoMapper.toItemPedidoResponse(listaItemPedido)).thenReturn(listaItemPedidoDTO);
//
//        PedidoDTO mapperPedidoDTO = pedidoMapper.toPedidoDTO(pedido);
//        assertThat(mapperPedidoDTO).isNotNull();
//    }
//
//    @Test
//    void toPedidoDTO_semCliente() {
//        var pedidoDTO = getPedidoDTO();
//        var pedido = getPedidoSemCliente();
//        var listaItemPedido = List.of(getItemPedido(pedido));
//        var listaItemPedidoDTO = pedidoDTO.itens();
//
//        when(itemPedidoMapper.toItemPedidoResponse(listaItemPedido)).thenReturn(listaItemPedidoDTO);
//
//        PedidoDTO mapperPedidoDTO = pedidoMapper.toPedidoDTO(pedido);
//        assertThat(mapperPedidoDTO).isNotNull();
//    }
//
//}