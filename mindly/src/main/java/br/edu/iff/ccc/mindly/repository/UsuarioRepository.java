// src/main/java/br/edu/iff/ccc/mindly/repository/UsuarioRepository.java
package br.edu.iff.ccc.mindly.repository;

import br.edu.iff.ccc.mindly.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}