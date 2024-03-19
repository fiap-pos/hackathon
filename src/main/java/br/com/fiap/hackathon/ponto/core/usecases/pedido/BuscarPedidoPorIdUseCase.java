//package br.com.fiap.hackathon.ponto.core.usecases.pedido;
//
//import br.com.fiap.hackathon.ponto.core.ports.in.pedido.BuscarPedidoPorIdInputPort;
//import br.com.fiap.hackathon.ponto.core.ports.out.pedido.BuscarPedidoPorIdOutputPort;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BuscarPedidoPorIdUseCase implements BuscarPedidoPorIdInputPort {
//    private final BuscarPedidoPorIdOutputPort buscarPedidoPorIdOutputPort;
//    public BuscarPedidoPorIdUseCase(BuscarPedidoPorIdOutputPort buscarPedidoPorIdOutputPort) {
//        this.buscarPedidoPorIdOutputPort = buscarPedidoPorIdOutputPort;
//    }
//    @Override
//    public PedidoDTO buscarPorId(Long id) {
//        return buscarPedidoPorIdOutputPort.buscarPorId(id);
//    }
//}
