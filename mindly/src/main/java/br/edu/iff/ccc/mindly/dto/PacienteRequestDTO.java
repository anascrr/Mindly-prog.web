package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Paciente;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


// DTO para RECEBER os dados de criação de um novo Paciente.
// Não possui o campo ID, pois ele será gerado pelo banco de dados.

public class PacienteRequestDTO {

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

    public PacienteRequestDTO() {
    }

    public Paciente toEntity() {
        Paciente paciente = new Paciente();
        // O ID não é definido aqui, será gerado pelo banco
        paciente.setNome(this.nome);
        paciente.setEmail(this.email);
        paciente.setTelefone(this.telefone);
        paciente.setEndereco(this.endereco);
        paciente.setDataNascimento(this.dataNascimento);
        paciente.setCpf(this.cpf);
        paciente.setPlanoSaude(this.planoSaude);
        return paciente;
    }

    // Getters e Setters para todos os campos (exceto id)
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