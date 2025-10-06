package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Consulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO para enviar dados de uma consulta como resposta da API")
public record ConsultaResponseDTO(
        @Schema(description = "ID único da consulta", example = "1")
        Long id,

        @Schema(description = "ID do paciente associado à consulta", example = "101")
        Long pacienteId,

        @Schema(description = "Nome do paciente associado à consulta", example = "Ana Costa")
        String pacienteNome,

        @Schema(description = "Nome do médico responsável pela consulta", example = "Dr. Carlos Silva")
        String medico, // Mantendo como String, conforme a entidade

        @Schema(description = "Data e hora da consulta (DD/MM/AAAA HH:MM)", example = "25/12/2024 10:30")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataConsulta,

        @Schema(description = "Observações adicionais sobre a consulta", example = "Primeira sessão, avaliação inicial.")
        String observacao) {

    public static ConsultaResponseDTO fromEntity(Consulta consulta) {
        Long pacienteId = (consulta.getPaciente() != null) ? consulta.getPaciente().getId() : null;
        String pacienteNome = (consulta.getPaciente() != null) ? consulta.getPaciente().getNome() : "Paciente não associado";

        return new ConsultaResponseDTO(
                consulta.getId(),
                pacienteId,
                pacienteNome,
                consulta.getMedico(), // Pegando o nome do médico diretamente da entidade
                consulta.getDataConsulta(),
                consulta.getObservacao());
    }
}