package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Paciente;
import io.swagger.v3.oas.annotations.media.Schema; // Importar Schema


@Schema(description = "DTO para enviar dados de um paciente como resposta da API")
public class PacienteResponseDTO {

    @Schema(description = "ID único do paciente", example = "1111111")
    private Long id;

    @Schema(description = "Nome completo do paciente", example = "Maria da Silva")
    private String nome;

    @Schema(description = "Endereço de e-mail do paciente", example = "maria@mindly.com")
    private String email;

    @Schema(description = "Número de telefone do paciente", example = "(22) 99876-5432")
    private String telefone;

    @Schema(description = "Data de nascimento do paciente (DD/MM/AAAA)", example = "15/05/1990")
    private String dataNascimento;

    @Schema(description = "Nome do plano de saúde do paciente", example = "Unimed")
    private String planoSaude;

    public PacienteResponseDTO() {
    }

    public PacienteResponseDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
        this.dataNascimento = paciente.getDataNascimento();
        this.planoSaude = paciente.getPlanoSaude();
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
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getPlanoSaude() { return planoSaude; }
    public void setPlanoSaude(String planoSaude) { this.planoSaude = planoSaude; }
}