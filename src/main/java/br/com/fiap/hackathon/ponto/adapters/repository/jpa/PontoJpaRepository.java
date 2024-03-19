package br.com.fiap.hackathon.ponto.adapters.repository.jpa;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoJpaRepository extends JpaRepository<Ponto, Long> {
}
