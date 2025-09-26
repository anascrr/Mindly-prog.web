package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.dto.UsuarioCadastroDTO;
import br.edu.iff.ccc.mindly.dto.UsuarioResponseDTO;
import br.edu.iff.ccc.mindly.entities.Role;
import br.edu.iff.ccc.mindly.entities.Usuario;
import br.edu.iff.ccc.mindly.exception.BusinessException;
import br.edu.iff.ccc.mindly.exception.ForbiddenException;
import br.edu.iff.ccc.mindly.exception.ResourceNotFoundException;
import br.edu.iff.ccc.mindly.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- REMOVIDO: o método autenticar que recebia email e senha ---
    // public UsuarioResponseDTO autenticar(String email, String senha) { /* ... */ }

    // --- Métodos de CRUD para Usuário ---

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioCadastroDTO dto, String emailDoUsuarioLogado) {
        if (!isAdminSimulado(emailDoUsuarioLogado)) {
            throw new ForbiddenException("Apenas administradores podem criar novos usuários.");
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já existe: " + dto.getEmail());
        }

        Usuario novoUsuario = dto.toEntity();
        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioCadastroDTO dto, String emailDoUsuarioLogado) {
        if (!isAdminSimulado(emailDoUsuarioLogado)) {
            throw new ForbiddenException("Apenas administradores podem alterar dados de usuários.");
        }

        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        if (!usuarioExistente.getEmail().equals(dto.getEmail()) && usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já existe para outro usuário: " + dto.getEmail());
        }

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            usuarioExistente.setSenha(dto.getSenha());
        }
        usuarioExistente.setRole(dto.getRole());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return new UsuarioResponseDTO(usuarioAtualizado);
    }

    @Transactional
    public void excluirUsuario(Long id, String emailDoUsuarioLogado) {
        if (!isAdminSimulado(emailDoUsuarioLogado)) {
            throw new ForbiddenException("Apenas administradores podem excluir usuários.");
        }

        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado para exclusão com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario criarUsuarioInicial(String nome, String email, String senha, Role role) {
        if (!usuarioRepository.existsByEmail(email)) {
            Usuario novoUsuario = new Usuario(nome, email, senha, role);
            return usuarioRepository.save(novoUsuario);
        }
        return usuarioRepository.findByEmail(email).get();
    }

    // SIMULAÇÃO: Verificador de Role Admin (ainda precisa de um usuário no banco para funcionar)
    private boolean isAdminSimulado(String emailDoUsuarioLogado) {
        if (emailDoUsuarioLogado == null || emailDoUsuarioLogado.isEmpty()) {
            return false;
        }
        // Buscar o usuário pelo email e verificar se ele é ADMIN
        return usuarioRepository.findByEmail(emailDoUsuarioLogado)
                .map(Usuario::isAdmin)
                .orElse(false);
    }

    public long countUsuarios() {
        return usuarioRepository.count();
    }
}