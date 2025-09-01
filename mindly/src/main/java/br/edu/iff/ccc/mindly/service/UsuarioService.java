package br.edu.iff.ccc.mindly.service;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.mindly.dto.UsuarioDTO;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UsuarioService {

    private final Map<Long, UsuarioDTO> usuarios = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // login simples em memória
    public UsuarioDTO autenticar(String username, String senha) {
        return usuarios.values().stream()
                .filter(u -> u.getUsername().equals(username) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    public UsuarioDTO salvar(UsuarioDTO dto) {
        if (dto.getId() == null) {
            dto.setId(idCounter.getAndIncrement());
        }
        usuarios.put(dto.getId(), dto);
        return dto;
    }

    public List<UsuarioDTO> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }
}
