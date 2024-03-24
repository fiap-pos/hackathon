//package br.com.fiap.hackathon.ponto.adapters.web;
//
//import br.com.fiap.hackathon.ponto.adapters.web.mappers.PedidoMapper;
//import br.com.fiap.hackathon.ponto.adapters.web.models.requests.PedidoRequest;
//import br.com.fiap.hackathon.ponto.adapters.web.models.responses.PedidoResponse;
//import br.com.fiap.hackathon.ponto.core.ports.in.pedido.*;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Tag(name = "Pedido", description = "APIs para gerenciamento de Pedido")
//@RestController
//@RequestMapping("/pedidos")
//public class PedidoController extends ControllerBase{
//
//    private final CriaPedidoInputPort criaPedidoInputPort;
//    private final BuscaTodosPedidosInputPort buscaTodosPedidosInputPort;
//    private final BuscarPedidoPorIdInputPort buscarPedidoPorIdInputPort;
//    private final BuscaTodosPedidosPorStatusInputPort buscaTodosPedidosPorStatusInputPort;
//    private final PedidoMapper pedidoMapper;
//
//    public PedidoController(CriaPedidoInputPort criaPedidoInputPort,
//                            BuscaTodosPedidosInputPort buscaTodosPedidosInputPort,
//                            BuscarPedidoPorIdInputPort buscarPedidoPorIdInputPort,
//                            BuscaTodosPedidosPorStatusInputPort buscaTodosPedidosPorStatusInputPort,
//                            PedidoMapper pedidoMapper
//    ) {
//        this.criaPedidoInputPort = criaPedidoInputPort;
//        this.buscaTodosPedidosInputPort = buscaTodosPedidosInputPort;
//        this.buscarPedidoPorIdInputPort = buscarPedidoPorIdInputPort;
//        this.buscaTodosPedidosPorStatusInputPort = buscaTodosPedidosPorStatusInputPort;
//        this.pedidoMapper = pedidoMapper;
//    }
//
//    @Operation(summary = "Busca todos os pedidos")
//    @GetMapping
//    public ResponseEntity<List<PedidoResponse>> buscarTodos(){
//        var pedidosOut = buscaTodosPedidosInputPort.buscarTodos();
//        var listPedidoResponse = pedidoMapper.toPedidoListResponse(pedidosOut);
//        return ResponseEntity.ok(listPedidoResponse);
//    }
//
//    @Operation(summary = "Busca pedido pelo id")
//    @GetMapping("/{id}")
//    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable("id") Long id){
//        var pedidoOut = buscarPedidoPorIdInputPort.buscarPorId(id);
//        var pedidoResponse = pedidoMapper.toPedidoResponse(pedidoOut);
//        return ResponseEntity.ok(pedidoResponse);
//    }
//
//    @Operation(summary = "Cria um pedido")
//    @PostMapping
//    public ResponseEntity<PedidoResponse> criarPedido(@Valid @RequestBody PedidoRequest pedidoRequest, @RequestHeader("Authorization") String authorization){
//        var pedidoOut = criaPedidoInputPort.criar(pedidoRequest.toCriaItemPedidoDTO(), authorization);
//        var pedidoResponse = pedidoMapper.toPedidoResponse(pedidoOut);
//        var uri = getExpandedCurrentUri("/{id}", pedidoResponse.getId());
//        return ResponseEntity.created(uri).body(pedidoResponse);
//    }
//
//    @Operation(summary = "Busca todos os pedidos por status")
//    @GetMapping(value = "/status/{status}")
//    public ResponseEntity<List<PedidoResponse>> buscarTodos(@PathVariable("status") String status){
//        var pedidosOut = buscaTodosPedidosPorStatusInputPort.buscarTodosStatus(StatusPedidoEnum.fromString(status))
//                .stream()
//                .map(pedidoMapper::toPedidoResponse)
//                .toList();
//        return ResponseEntity.ok(pedidosOut);
//    }
//}
