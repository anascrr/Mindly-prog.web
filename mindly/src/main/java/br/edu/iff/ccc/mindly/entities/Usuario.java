// src/main/java/br/edu/iff/ccc/mindly/entities/Usuario.java
package br.edu.iff.ccc.mindly.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; // Nome completo do funcionário

    @Column(nullable = false, unique = true)
    private String email; // IDENTIFICADOR PRINCIPAL PARA LOGIN (e deve ser único)

    @Column(nullable = false)
    private String senha; // Senha (ainda não criptografada, será o 'password')

    // Usaremos um Enum para as roles para melhor controle e tipagem
    // @Enumerated(EnumType.STRING) armazena o nome da enum ("ADMIN", "PSICOLOGO", etc.) no banco
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, Role role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    // Método de conveniência para verificar se é ADMIN
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", email='" + email + '\'' +
               ", role=" + role +
               '}';
    }
}