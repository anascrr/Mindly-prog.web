package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;
import br.edu.iff.ccc.mindly.entities.Paciente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    private static List<Paciente> pacientes = new ArrayList<>();
    private static Long proximoId = 1L;

    public static List<Paciente> listarTodos() {
        return pacientes;
    }

    public static Paciente buscarPorId(Long id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    public static void adicionarPaciente(Paciente paciente) {
        paciente.setId(proximoId++);
        pacientes.add(paciente);
    }

    public static void atualizarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = buscarPorId(pacienteDTO.getId());
        if (paciente != null) {
            paciente.setNome(pacienteDTO.getNome());
            paciente.setEmail(pacienteDTO.getEmail());
            paciente.setTelefone(pacienteDTO.getTelefone());
            paciente.setEndereco(pacienteDTO.getEndereco());
            paciente.setDataNascimento(pacienteDTO.getDataNascimento());
            paciente.setCpf(pacienteDTO.getCpf());
            paciente.setPlanoSaude(pacienteDTO.getPlanoSaude());
        }
    }

    public static void excluirPaciente(Long id) {
        pacientes.removeIf(paciente -> paciente.getId().equals(id));
    }

    public static List<Paciente> listarPacientes() {
        return pacientes;
    }
}