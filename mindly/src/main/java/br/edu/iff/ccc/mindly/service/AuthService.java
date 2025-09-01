package br.edu.iff.ccc.mindly.service;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.mindly.dto.LoginDTO;
import br.edu.iff.ccc.mindly.entities.Login;

import java.util.List;

@Service
public class AuthService {

    private final List<Login> usuarios = List.of(
        new Login(1L, "Carlos Augusto" ,"recepcao@mindly.com", "123", "recepcionista"),
        new Login(2L, "Dr. Rafael Monteiro", "psicologo@mindly.com", "123", "psicologo"),
        new Login(3L, "Dra. Helena Vasconcelos", "psiquiatra@mindly.com", "123", "psiquiatra")
    );

    public Login autenticar(LoginDTO dto) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(dto.getEmail()) && u.getSenha().equals(dto.getSenha()))
                .findFirst()
                .orElse(null);
    }
}
