// src/main/java/br/edu/iff/ccc/mindly/dto/UsuarioCadastroDTO.java
package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Role; // Importa o Enum Role
import br.edu.iff.ccc.mindly.entities.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // Para o Enum Role
import jakarta.validation.constraints.Size;

public class UsuarioCadastroDTO {


    @Schema(description = "Nome do usuário", example = "Rafael Monteiro")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Schema(description = "Endereço de email do usuário", example = "psicologo@mindly.com")
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    private String email;

    @Schema(description = "Senha do usuário", example = "123456", accessMode = Schema.AccessMode.WRITE_ONLY)
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @Schema(description = "Papel (role) do usuário no sistema", example = "PSICOLOGO")
    @NotNull(message = "A role é obrigatória")
    private Role role;

    // --- Getters e Setters ---
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    // Método para converter para entidade
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setRole(this.role);
        return usuario;
    }
}