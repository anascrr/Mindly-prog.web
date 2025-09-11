package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.dto.LoginDTO;
import br.edu.iff.ccc.mindly.entities.Usuario;
import br.edu.iff.ccc.mindly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticar(LoginDTO loginDTO) {
        return usuarioRepository
                .findByEmail(loginDTO.getEmail())
                .orElse(null);
    }
}
