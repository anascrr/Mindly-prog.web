package br.edu.iff.ccc.mindly.repository;

import br.edu.iff.ccc.mindly.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByNome(String nome);
}
