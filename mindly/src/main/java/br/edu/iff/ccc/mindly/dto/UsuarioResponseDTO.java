// src/main/java/br/edu/iff/ccc/mindly/dto/UsuarioResponseDTO.java
package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Role; // Importa o Enum Role
import br.edu.iff.ccc.mindly.entities.Usuario;

public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private Role role; // Usa o Enum Role

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.role = usuario.getRole();
    }

    public UsuarioResponseDTO(Long id, String nome, String email, Role role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}