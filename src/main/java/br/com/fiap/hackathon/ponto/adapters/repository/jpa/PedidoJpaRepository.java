package br.com.fiap.hackathon.ponto.adapters.repository.jpa;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Pedido;
import br.com.fiap.hackathon.ponto.core.domain.entities.enums.StatusPedidoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoJpaRepository extends JpaRepository<Pedido, Long> {

   List<Pedido> findByStatus(StatusPedidoEnum status);

}
