package br.edu.iff.ccc.mindly.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ConsultaDTO {

    @NotNull(message = "O paciente é obrigatório")
    private Long pacienteId; 

    @NotNull(message = "A data da consulta é obrigatória")
    @FutureOrPresent(message = "A data da consulta deve ser no presente ou futuro")
    private LocalDateTime dataConsulta;

    @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
    private String observacao;

    @NotBlank(message = "O médico é obrigatório")
    private String medico; 

    public ConsultaDTO() {
    }

    public ConsultaDTO(Long pacienteId, String medico, LocalDateTime dataConsulta, String observacao) {
        this.pacienteId = pacienteId;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
        this.observacao = observacao;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public Object getId() {
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    public void setId(long andIncrement) {
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}