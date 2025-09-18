package br.edu.iff.ccc.mindly.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

// DTO para RECEBER dados ao criar/atualizar uma consulta.
// Renomeamos a classe e removemos os métodos getId/setId.
public class ConsultaRequestDTO {

    @NotNull(message = "O paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "A data da consulta é obrigatória")
    @FutureOrPresent(message = "A data da consulta deve ser no presente ou futuro")
    private LocalDateTime dataConsulta;

    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
    private String observacao;

    @NotBlank(message = "O médico é obrigatório")
    private String medico;

    // Construtores, Getters e Setters continuam os mesmos...
    public ConsultaRequestDTO() {
    }

    public ConsultaRequestDTO(Long pacienteId, String medico, LocalDateTime dataConsulta, String observacao) {
        this.pacienteId = pacienteId;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
        this.observacao = observacao;
    }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public LocalDateTime getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDateTime dataConsulta) { this.dataConsulta = dataConsulta; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }
}