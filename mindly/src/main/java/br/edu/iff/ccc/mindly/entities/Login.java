package br.edu.iff.ccc.mindly.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String senha;
    private String papel; // recepcionista, psicologo, psiquiatra

    // construtores
    public Login() {}

    public Login(Long id, String email, String senha, String papel) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getPapel() { return papel; }
    public void setPapel(String papel) { this.papel = papel; }
}
