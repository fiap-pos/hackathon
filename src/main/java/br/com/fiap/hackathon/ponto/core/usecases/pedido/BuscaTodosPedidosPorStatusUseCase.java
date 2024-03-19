//package br.com.fiap.hackathon.ponto.core.usecases.pedido;
//
//import br.com.fiap.hackathon.ponto.core.ports.in.pedido.BuscaTodosPedidosPorStatusInputPort;
//import br.com.fiap.hackathon.ponto.core.ports.out.pedido.BuscaTodosPedidosPorStatusOutputPort;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//@Component
//public class BuscaTodosPedidosPorStatusUseCase implements BuscaTodosPedidosPorStatusInputPort {
//
//    private final BuscaTodosPedidosPorStatusOutputPort buscaTodosPedidosPorStatusOutputPort;
//
//    public BuscaTodosPedidosPorStatusUseCase(BuscaTodosPedidosPorStatusOutputPort buscaTodosPedidosPorStatusOutputPort) {
//        this.buscaTodosPedidosPorStatusOutputPort = buscaTodosPedidosPorStatusOutputPort;
//    }
//
//    @Override
//    public List<PedidoDTO> buscarTodosStatus(StatusPedidoEnum status) {
//        return buscaTodosPedidosPorStatusOutputPort.buscarPedidosPorStatus(status);
//    }
//
//}
