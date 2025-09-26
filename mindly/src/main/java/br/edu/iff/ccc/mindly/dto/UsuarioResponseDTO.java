// src/main/java/br/edu/iff/ccc/mindly/dto/UsuarioResponseDTO.java
package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Role; // Importa o Enum Role
import br.edu.iff.ccc.mindly.entities.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioResponseDTO {
    @Schema(description = "ID único do usuário", example = "1111111")
    private Long id;
    @Schema(description = "Nome do usuário", example = "Rafael Monteiro")
    private String nome;
    @Schema(description = "Endereço de email do usuário", example = "psicologo@mindly.com")
    private String email;
    @Schema(description = "Papel (role) do usuário no sistema", example = "PSICOLOGO")
    private Role role;

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