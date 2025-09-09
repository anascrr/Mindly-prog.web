package br.edu.iff.ccc.mindly.repository;

import br.edu.iff.ccc.mindly.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Aqui você pode adicionar consultas personalizadas, por exemplo:
    Paciente findByEmail(String email);
}
