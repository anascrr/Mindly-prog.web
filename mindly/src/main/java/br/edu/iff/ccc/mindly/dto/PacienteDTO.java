package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Paciente;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class PacienteDTO {

    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\s]{3,}$", message = "Nome deve ter no mínimo 3 letras")
    private String nome;

    @NotEmpty(message = "E-mail não pode ser vazio")
    @Pattern(regexp = "^[\\w\\.-]+@(gmail\\.com(\\.br)?|hotmail\\.com(\\.br)?)$", message = "E-mail deve ser do domínio gmail.com, gmail.com.br, hotmail.com ou hotmail.com.br")
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
    @Size(min = 14, max = 14, message = "CPF deve ter 11 números")
    private String cpf;

    @NotEmpty(message = "Plano de saúde não pode ser vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\s]{3,}$", message = "Plano de saúde deve ter no mínimo 3 caracteres")
    private String planoSaude;

    public PacienteDTO() {
    }

    public PacienteDTO(Paciente paciente) {
        if (paciente != null) {
            this.id = paciente.getId();
            this.nome = paciente.getNome();
            this.email = paciente.getEmail();
            this.telefone = paciente.getTelefone();
            this.endereco = paciente.getEndereco();
            this.dataNascimento = paciente.getDataNascimento();
            this.cpf = paciente.getCpf();
            this.planoSaude = paciente.getPlanoSaude();
        }
    }

    public Paciente toEntity() {
        return new Paciente(
                this.id,
                this.nome,
                this.email,
                this.telefone,
                this.endereco,
                this.dataNascimento,
                this.cpf,
                this.planoSaude);
    }

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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
}
