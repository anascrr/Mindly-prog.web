package br.edu.iff.ccc.mindly.repository;

import br.edu.iff.ccc.mindly.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    // Método para encontrar consultas por médico e data (exatamente igual)
    List<Consulta> findByMedicoAndDataConsulta(String medico, LocalDateTime dataConsulta);

    List<Consulta> findByDataConsultaBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Consulta> findByPacienteIdOrderByDataConsultaDesc(Long pacienteId);
}
