package br.edu.iff.ccc.mindly.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Schema(description = "DTO para receber dados ao criar ou atualizar uma consulta")
public class ConsultaRequestDTO {

    @Schema(description = "ID do paciente associado à consulta", example = "1", required = true)
    @NotNull(message = "O ID do paciente é obrigatório")
    private Long pacienteId;

    @Schema(description = "Nome do médico que realizará a consulta", example = "Dr. Carlos Silva", required = true)
    @NotBlank(message = "O nome do médico é obrigatório")
    private String medico;

    @Schema(description = "Data e hora da consulta (formato ISO 8601)", example = "2024-12-25T10:30:00", required = true)
    @NotNull(message = "A data da consulta é obrigatória")
    @FutureOrPresent(message = "A data da consulta deve ser no presente ou futuro")
    private LocalDateTime dataConsulta;

    @Schema(description = "Observações adicionais sobre a consulta", example = "Retorno para acompanhamento.")
    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
    private String observacao;

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

    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }

    public LocalDateTime getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDateTime dataConsulta) { this.dataConsulta = dataConsulta; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}