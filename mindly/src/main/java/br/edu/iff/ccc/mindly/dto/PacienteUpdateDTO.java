package br.edu.iff.ccc.mindly.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


// DTO para RECEBER os dados de atualização de um Paciente existente.
// Contém todas as validações e o campo ID é obrigatório.

public class PacienteUpdateDTO {

    @NotNull(message = "ID não pode ser nulo para atualização")
    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{3,}$", message = "Nome deve ter no mínimo 3 letras")
    private String nome;

    @NotEmpty(message = "E-mail não pode ser vazio")
    @Pattern(regexp = "^[\\w\\.-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Formato de e-mail inválido")
    private String email;

    @NotEmpty(message = "Telefone não pode ser vazio")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Telefone deve estar no formato (99) 99999-9999")
    private String telefone;

    @NotEmpty(message = "Endereço não pode ser vazio")
    private String endereco;

    @NotEmpty(message = "Data de nascimento não pode ser vazia")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de nascimento deve estar no formato dd/MM/yyyy")
    private String dataNascimento;

    @NotEmpty(message = "CPF não pode ser vazio")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF deve estar no formato XXX.XXX.XXX-XX")
    private String cpf;

    @NotEmpty(message = "Plano de saúde não pode ser vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{3,}$", message = "Plano de saúde deve ter no mínimo 3 caracteres")
    private String planoSaude;

    public PacienteUpdateDTO() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getPlanoSaude() { return planoSaude; }
    public void setPlanoSaude(String planoSaude) { this.planoSaude = planoSaude; }
}