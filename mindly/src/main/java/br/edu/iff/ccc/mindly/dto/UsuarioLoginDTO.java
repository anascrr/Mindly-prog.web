// src/main/java/br/edu/iff/ccc/mindly/dto/UsuarioLoginDTO.java
package br.edu.iff.ccc.mindly.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para autenticação de usuário (login)")
public class UsuarioLoginDTO {

    @Schema(description = "Endereço de email do usuário", example = "psicologo@mindly.com")
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    @Schema(description = "Senha do usuário", example = "123", accessMode = Schema.AccessMode.WRITE_ONLY)
    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    // --- Getters e Setters ---
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}