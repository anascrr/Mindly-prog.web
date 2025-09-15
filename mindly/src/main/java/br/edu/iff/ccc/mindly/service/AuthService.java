package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.dto.LoginDTO;
import br.edu.iff.ccc.mindly.entities.Usuario;
import br.edu.iff.ccc.mindly.exception.InvalidCredentialsException;
import br.edu.iff.ccc.mindly.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario autenticar(LoginDTO dto) {
        return usuarioRepository.findByEmail(dto.getEmail())
                .filter(u -> u.getSenha().equals(dto.getSenha()))
                .orElseThrow(() -> new InvalidCredentialsException("Email ou senha inválidos"));
    }
}
 