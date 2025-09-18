package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Paciente;


// DTO para ENVIAR os dados de um Paciente como resposta da API.
// Expõe apenas os campos necessários, protegendo dados como CPF e Endereço.

public class PacienteResponseDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String dataNascimento;
    private String planoSaude;

    public PacienteResponseDTO() {
    }

    // Este construtor é a forma principal de criar este DTO.
    // Ele extrai os dados da entidade Paciente.
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