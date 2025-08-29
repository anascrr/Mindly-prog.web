package br.edu.iff.ccc.mindly.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Telefone não pode ser vazio")
    private String telefone;

    @NotEmpty(message = "Endereço não pode ser vazio")
    private String endereco;

    @NotNull(message = "Data de nascimento não pode ser vazio")
    @Past
    private LocalDate dataNascimento;

    @NotEmpty(message = "CPF não pode ser vazio")
    @Size(min = 11, max = 11, message = "Adicione apenas os números do CPF")
    private String cpf;

    @NotEmpty(message = "Plano de saúde não pode ser vazio")
    private String planoSaude;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public @NotNull(message = "Data de nascimento não pode ser vazio") @Past LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull(message = "Data de nascimento não pode ser vazio") @Past LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void adicionarConsulta(Consulta consulta) {
        this.consultas.add(consulta);
    }

    public Paciente(Long id, String nome, String email, String telefone, String endereco, @NotNull(message = "Data de nascimento não pode ser vazio") @Past LocalDate dataNascimento,
            String cpf, String planoSaude) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.planoSaude = planoSaude;
    }
}
