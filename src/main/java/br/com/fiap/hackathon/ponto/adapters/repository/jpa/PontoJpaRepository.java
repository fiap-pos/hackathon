package br.com.fiap.hackathon.ponto.adapters.repository.jpa;

import br.com.fiap.hackathon.ponto.adapters.repository.models.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PontoJpaRepository extends JpaRepository<Ponto, Long> {

    @Query("SELECT p FROM Ponto p WHERE p.matricula = :matricula AND p.registro BETWEEN :startDate AND :endDate ORDER BY p.registro ASC")
    List<Ponto> findByMatriculaAndRegistroBetween(String matricula, LocalDateTime startDate, LocalDateTime endDate);
}
