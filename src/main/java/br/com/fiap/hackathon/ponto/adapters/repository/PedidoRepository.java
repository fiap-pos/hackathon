//package br.com.fiap.hackathon.ponto.adapters.repository;
//
//import br.com.fiap.hackathon.ponto.adapters.gateways.producao.mappers.FilaProducaoMapper;
//import br.com.fiap.hackathon.ponto.adapters.repository.mappers.PedidoMapper;
//import br.com.fiap.hackathon.ponto.adapters.repository.models.Pedido;
//import br.com.fiap.hackathon.ponto.adapters.repository.sqs.PedidoCriadoSqsPublisher;
//import br.com.fiap.hackathon.ponto.core.domain.exceptions.EntityNotFoundException;
//import br.com.fiap.hackathon.ponto.core.exceptions.UnexpectedDomainException;
//import br.com.fiap.hackathon.ponto.core.ports.out.pedido.AtualizaStatusPedidoOutputPort;
//import br.com.fiap.hackathon.ponto.core.ports.out.pedido.BuscaTodosPedidosOutputPort;
//import br.com.fiap.hackathon.ponto.core.ports.out.pedido.BuscaTodosPedidosPorStatusOutputPort;
//import br.com.fiap.hackathon.ponto.core.ports.out.pedido.BuscarPedidoPorIdOutputPort;
//import br.com.fiap.hackathon.ponto.core.ports.out.pedido.CriaPedidoOutputPort;
//import br.com.fiap.hackathon.ponto.core.ports.out.pedido.EnviaPedidoFilaProducaoOutputPort;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class PedidoRepository implements CriaPedidoOutputPort, AtualizaStatusPedidoOutputPort,
//        BuscaTodosPedidosOutputPort, BuscarPedidoPorIdOutputPort, BuscaTodosPedidosPorStatusOutputPort, EnviaPedidoFilaProducaoOutputPort {
//
//    private final Logger logger = LoggerFactory.getLogger(PedidoRepository.class);
//    private final PedidoMapper pedidoMapper;
//
//    private final FilaProducaoMapper filaProducaoMapper;
//
//    private final PedidoJpaRepository pedidoJpaRepository;
//
//    private final PedidoCriadoSqsPublisher pedidoCriadoSqsPublisher;
//
//    public PedidoRepository(PedidoMapper pedidoMapper, FilaProducaoMapper filaProducaoMapper, PedidoJpaRepository pedidoJpaRepository, PedidoCriadoSqsPublisher pedidoCriadoSqsPublisher) {
//        this.pedidoMapper = pedidoMapper;
//        this.filaProducaoMapper = filaProducaoMapper;
//        this.pedidoJpaRepository = pedidoJpaRepository;
//        this.pedidoCriadoSqsPublisher = pedidoCriadoSqsPublisher;
//    }
//
//    @Override
//    public List<PedidoDTO> buscarTodos() {
//        var listaPedidos = pedidoJpaRepository.findAll();
//
//        return listaPedidos.stream().map(pedidoMapper::toPedidoDTO).toList();
//    }
//
//    @Override
//    public PedidoDTO criar(PedidoDTO pedidoIn) {
//        var pedido = pedidoMapper.toPedido(pedidoIn);
//        var pedidoSalvo = pedidoJpaRepository.save(pedido);
//        return pedidoMapper.toPedidoDTO(pedidoSalvo);
//    }
//
//    @Override
//    public PedidoDTO atualizarStatus(Long id, StatusPedidoEnum status) {
//        var pedidoBuscado = buscarPedidoPorId(id);
//        pedidoBuscado.setStatus(status);
//        var pedido = pedidoJpaRepository.save(pedidoBuscado);
//        return pedidoMapper.toPedidoDTO(pedido);
//    }
//
//    @Override
//    public PedidoDTO buscarPorId(Long id) {
//        var pedidoBuscado = buscarPedidoPorId(id);
//        return pedidoMapper.toPedidoDTO(pedidoBuscado);
//    }
//
//
//    private Pedido buscarPedidoPorId(Long id){
//        return pedidoJpaRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Pedido " + id + " não encontrado"));
//    }
//
//    @Override
//    public List<PedidoDTO> buscarPedidosPorStatus(StatusPedidoEnum status) {
//        return pedidoJpaRepository.findByStatus(status).stream()
//                .map(pedidoMapper::toPedidoDTO)
//                .toList();
//    }
//
//    @Override
//    public void enviarPedido(PedidoDTO pedidoDTO) {
//        try {
//            var filaProducaoDTO = filaProducaoMapper.toFilaProducaoDTO(pedidoDTO);
//            pedidoCriadoSqsPublisher.publicaFilaPedidoCriado(filaProducaoDTO);
//        } catch (JsonProcessingException e) {
//            logger.error(e.getMessage(), e);
//            throw new UnexpectedDomainException("Erro ao publicar pedido na fila de produção");
//        }
//    }
//}
