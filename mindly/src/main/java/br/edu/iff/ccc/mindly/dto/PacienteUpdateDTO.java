package br.edu.iff.ccc.mindly.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;


@Schema(description = "DTO para receber dados de atualização de um paciente")
public class PacienteUpdateDTO {

    @Schema(description = "Nome completo do paciente", example = "Maria da Silva")
    @NotEmpty(message = "Nome não pode ser vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{3,}$", message = "Nome deve ter no mínimo 3 letras")
    private String nome;

    @Schema(description = "Endereço de e-mail do paciente", example = "maria@mindly.com", format = "email")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @Schema(description = "Número de telefone do paciente", example = "(22) 99999-9999")
    @NotEmpty(message = "Telefone não pode ser vazio")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Telefone deve estar no formato (99) 99999-9999")
    private String telefone;

    @Schema(description = "Endereço completo do paciente", example = "Rua Nova, 456, Bairro Novo, Outra Cidade")
    @NotEmpty(message = "Endereço não pode ser vazio")
    private String endereco;

    @Schema(description = "Data de nascimento do paciente (DD/MM/AAAA)", example = "20/10/1985")
    @NotEmpty(message = "Data de nascimento não pode ser vazia")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de nascimento deve estar no formato dd/MM/yyyy")
    private String dataNascimento;

    @Schema(description = "CPF do paciente (formato XXX.XXX.XXX-XX)", example = "987.654.321-11")
    @NotEmpty(message = "CPF não pode ser vazio")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF deve estar no formato XXX.XXX.XXX-XX")
    private String cpf;

    @Schema(description = "Nome do plano de saúde do paciente", example = "Bradesco Saúde")
    @NotEmpty(message = "Plano de saúde não pode ser vazio")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]{3,}$", message = "Plano de saúde deve ter no mínimo 3 caracteres")
    private String planoSaude;

    public PacienteUpdateDTO() {
    }

    // Getters e Setters
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