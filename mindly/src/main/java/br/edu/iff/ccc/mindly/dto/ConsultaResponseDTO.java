package br.edu.iff.ccc.mindly.dto;

import br.edu.iff.ccc.mindly.entities.Consulta;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

// Usando um record para o DTO de resposta, pois é mais conciso.
public record ConsultaResponseDTO(
        Long id,
        String pacienteNome,
        String medico,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataConsulta,
        String observacao
) {
    public static ConsultaResponseDTO fromEntity(Consulta consulta) {
        String nome = (consulta.getPaciente() != null) ? consulta.getPaciente().getNome() : "Paciente não associado";
        return new ConsultaResponseDTO(
                consulta.getId(),
                nome,
                consulta.getMedico(),
                consulta.getDataConsulta(),
                consulta.getObservacao()
        );
    }
}