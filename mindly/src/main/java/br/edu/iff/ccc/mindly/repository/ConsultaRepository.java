package br.edu.iff.ccc.mindly.repository;

import br.edu.iff.ccc.mindly.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByDataConsultaBetween(LocalDateTime inicio, LocalDateTime fim);
}
