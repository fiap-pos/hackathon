//package br.com.fiap.hackathon.ponto.adapters.repository;
//
//import br.com.fiap.hackathon.ponto.adapters.gateways.producao.mappers.FilaProducaoMapper;
//import br.com.fiap.hackathon.ponto.adapters.repository.mappers.PedidoMapper;
//import br.com.fiap.hackathon.ponto.adapters.repository.models.Pedido;
//import br.com.fiap.hackathon.ponto.adapters.repository.sqs.PedidoCriadoSqsPublisher;
//import br.com.fiap.hackathon.ponto.core.domain.exceptions.EntityNotFoundException;
//import br.com.fiap.hackathon.ponto.core.exceptions.UnexpectedDomainException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//import static br.com.fiap.hackathon.ponto.utils.FilaProducaoHelper.getFilaProducaoDTO;
//import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getPedido;
//import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getPedidoDTO;
//import static br.com.fiap.hackathon.ponto.utils.PedidoHelper.getPedidoDTOcomStatus;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class PedidoRepositoryTest {
//
//    private PedidoRepository pedidoRepository;
//
//    @Mock
//    PedidoJpaRepository pedidoJpaRepository;
//
//    @Mock
//    PedidoMapper pedidoMapper;
//
//    @Mock
//    FilaProducaoMapper filaProducaoMapper;
//
//    @Mock
//    PedidoCriadoSqsPublisher pedidoCriadoSqsPublisher;
//
//    AutoCloseable openMocks;
//
//    @BeforeEach
//    void setup() {
//        openMocks = MockitoAnnotations.openMocks(this);
//        pedidoRepository = new PedidoRepository(pedidoMapper, filaProducaoMapper, pedidoJpaRepository, pedidoCriadoSqsPublisher);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        openMocks.close();
//    }
//
//    @Nested
//    class pedidos {
//
//        @Test
//        void buscarTodos() {
//            var pedido = getPedido();
//            var listaPedidos = List.of(pedido);
//            var pedidoDTO = getPedidoDTO();
//
//            when(pedidoJpaRepository.findAll()).thenReturn(listaPedidos);
//            when(pedidoMapper.toPedidoDTO(pedido)).thenReturn(pedidoDTO);
//
//            var listaPedidosEncontrados = pedidoRepository.buscarTodos();
//
//            assertThat(listaPedidosEncontrados).isNotNull();
//
//            verify(pedidoJpaRepository, times(1)).findAll();
//            verify(pedidoMapper, times(1)).toPedidoDTO(any(Pedido.class));
//        }
//
//        @Test
//        void criar() {
//            var pedidoDTO = getPedidoDTO();
//            var pedido = getPedido();
//
//            when(pedidoMapper.toPedido(pedidoDTO)).thenReturn(pedido);
//            when(pedidoJpaRepository.save(pedido)).thenReturn(pedido);
//            when(pedidoMapper.toPedidoDTO(pedido)).thenReturn(pedidoDTO);
//
//            PedidoDTO pedidoCriado = pedidoRepository.criar(pedidoDTO);
//
//            assertThat(pedidoCriado).isNotNull().isInstanceOf(PedidoDTO.class);
//            assertThat(pedidoCriado.id()).isEqualTo(pedidoDTO.id());
//            assertThat(pedidoCriado.getNomeCliente()).isEqualTo(pedidoDTO.getNomeCliente());
//            assertThat(pedidoCriado.itens()).allSatisfy( item -> {
//               assertThat(item.produtoId()).isEqualTo(pedidoDTO.itens().get(0).produtoId());
//               assertThat(item.produtoNome()).isEqualTo(pedidoDTO.itens().get(0).produtoNome());
//               assertThat(item.produtoDescricao()).isEqualTo(pedidoDTO.itens().get(0).produtoDescricao());
//               assertThat(item.valorUnitario()).isEqualTo(pedidoDTO.itens().get(0).valorUnitario());
//               assertThat(item.quantidade()).isEqualTo(pedidoDTO.itens().get(0).quantidade());
//            });
//            assertThat(pedidoCriado.valorTotal()).isEqualTo(pedidoDTO.valorTotal());
//            assertThat(pedidoCriado.dataCriacao()).isEqualTo(pedidoDTO.dataCriacao());
//
//            verify(pedidoMapper, times(1)).toPedido(pedidoDTO);
//            verify(pedidoJpaRepository, times(1)).save(pedido);
//            verify(pedidoMapper, times(1)).toPedidoDTO(pedido);
//        }
//
//        @Test
//        void atualizarStatus() {
//            var id = 1L;
//            var pedido = getPedido();
//            var statusNovo = StatusPedidoEnum.RECEBIDO;
//            var pedidoDTOComStatusAtualizado = getPedidoDTOcomStatus(statusNovo);
//
//            when(pedidoJpaRepository.findById(id)).thenReturn(Optional.of(pedido));
//            when(pedidoJpaRepository.save(pedido)).thenReturn(pedido);
//            when(pedidoMapper.toPedidoDTO(pedido)).thenReturn(pedidoDTOComStatusAtualizado);
//
//            PedidoDTO pedidoComStatusAtualizado = pedidoRepository.atualizarStatus(id, statusNovo);
//
//            assertThat(pedidoComStatusAtualizado).isNotNull().isInstanceOf(PedidoDTO.class);
//            assertThat(pedidoComStatusAtualizado.id()).isEqualTo(pedidoDTOComStatusAtualizado.id());
//            assertThat(pedidoComStatusAtualizado.status()).isEqualTo(pedidoDTOComStatusAtualizado.status());
//
//            verify(pedidoJpaRepository, times(1)).findById(id);
//            verify(pedidoJpaRepository, times(1)).save(pedido);
//            verify(pedidoMapper, times(1)).toPedidoDTO(pedido);
//        }
//
//        @Test
//        void buscarPorId_ComSucesso() {
//            var id = 1L;
//            var pedido = getPedido();
//            var pedidoDTO = getPedidoDTO();
//
//            when(pedidoJpaRepository.findById(id)).thenReturn(Optional.of(pedido));
//            when(pedidoMapper.toPedidoDTO(pedido)).thenReturn(pedidoDTO);
//
//            PedidoDTO pedidoEncontrado = pedidoRepository.buscarPorId(id);
//
//            assertThat(pedidoEncontrado).isNotNull().isInstanceOf(PedidoDTO.class);
//            assertThat(pedidoEncontrado.id()).isEqualTo(pedidoDTO.id());
//            assertThat(pedidoEncontrado.getNomeCliente()).isEqualTo(pedidoDTO.getNomeCliente());
//            assertThat(pedidoEncontrado.itens()).allSatisfy( item -> {
//                assertThat(item.produtoId()).isEqualTo(pedidoDTO.itens().get(0).produtoId());
//                assertThat(item.produtoNome()).isEqualTo(pedidoDTO.itens().get(0).produtoNome());
//                assertThat(item.produtoDescricao()).isEqualTo(pedidoDTO.itens().get(0).produtoDescricao());
//                assertThat(item.valorUnitario()).isEqualTo(pedidoDTO.itens().get(0).valorUnitario());
//                assertThat(item.quantidade()).isEqualTo(pedidoDTO.itens().get(0).quantidade());
//            });
//            assertThat(pedidoEncontrado.valorTotal()).isEqualTo(pedidoDTO.valorTotal());
//            assertThat(pedidoEncontrado.dataCriacao()).isEqualTo(pedidoDTO.dataCriacao());
//
//            verify(pedidoJpaRepository, times(1)).findById(id);
//            verify(pedidoMapper, times(1)).toPedidoDTO(pedido);
//        }
//
//        @Test
//        void buscarPorId_NaoEncontrado_LancaExcecao() {
//            var id = 1L;
//            var pedido = getPedido();
//            var pedidoDTO = getPedidoDTO();
//
//            when(pedidoJpaRepository.findById(id)).thenReturn(Optional.empty());
//            when(pedidoMapper.toPedidoDTO(pedido)).thenReturn(pedidoDTO);
//
//            assertThatThrownBy(() -> pedidoRepository.buscarPorId( id))
//                    .isInstanceOf(EntityNotFoundException.class)
//                    .hasMessage("Pedido " + id + " não encontrado");
//
//            verify(pedidoJpaRepository, times(1)).findById(anyLong());
//            verify(pedidoMapper, never()).toPedidoDTO(any(Pedido.class));
//        }
//
//        @Test
//        void buscarPedidosPorStatus() {
//            var pedido = getPedido();
//            var listaPedidos = List.of(pedido);
//            var pedidoDTO = getPedidoDTO();
//            var status = StatusPedidoEnum.PENDENTE_DE_PAGAMENTO;
//
//            when(pedidoJpaRepository.findByStatus(status)).thenReturn(listaPedidos);
//            when(pedidoMapper.toPedidoDTO(pedido)).thenReturn(pedidoDTO);
//
//            var listaPedidosEncontrados = pedidoRepository.buscarPedidosPorStatus(status);
//
//            assertThat(listaPedidosEncontrados).isNotNull();
//
//            verify(pedidoJpaRepository, times(1)).findByStatus(status);
//            verify(pedidoMapper, times(1)).toPedidoDTO(any(Pedido.class));
//        }
//
//        @Test
//        void enviarPedido() throws JsonProcessingException {
//            var pedidoDTO = getPedidoDTO();
//            var filaProducaoDTO = getFilaProducaoDTO();
//
//            when(filaProducaoMapper.toFilaProducaoDTO(pedidoDTO)).thenReturn(filaProducaoDTO);
//            doNothing().when(pedidoCriadoSqsPublisher).publicaFilaPedidoCriado(filaProducaoDTO);
//
//            pedidoRepository.enviarPedido(pedidoDTO);
//
//            verify(filaProducaoMapper, times(1)).toFilaProducaoDTO(pedidoDTO);
//            verify(pedidoCriadoSqsPublisher, times(1)).publicaFilaPedidoCriado(filaProducaoDTO);
//        }
//
//        @Test
//        void enviarPedido_ErroJson_LancaExcecao() throws JsonProcessingException {
//            var pedidoDTO = getPedidoDTO();
//            var filaProducaoDTO = getFilaProducaoDTO();
//
//            when(filaProducaoMapper.toFilaProducaoDTO(pedidoDTO)).thenReturn(filaProducaoDTO);
//            doNothing().when(pedidoCriadoSqsPublisher).publicaFilaPedidoCriado(filaProducaoDTO);
//
//            doThrow(JsonProcessingException.class).when(pedidoCriadoSqsPublisher).publicaFilaPedidoCriado(filaProducaoDTO);
//
//            assertThrows(UnexpectedDomainException.class, () -> pedidoRepository.enviarPedido(pedidoDTO));
//
//            verify(filaProducaoMapper, times(1)).toFilaProducaoDTO(pedidoDTO);
//            verify(pedidoCriadoSqsPublisher, times(1)).publicaFilaPedidoCriado(filaProducaoDTO);
//        }
//
//    }
//}