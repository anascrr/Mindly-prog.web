package br.edu.iff.ccc.mindly.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;
import br.edu.iff.ccc.mindly.entities.Paciente;
import jakarta.validation.Valid;

public class PacienteService {
    public void salvarPaciente(PacienteDTO dto) {
    }
    
    public static List<Paciente> pacientes = new ArrayList<>(); {
    }
    public List<Paciente> listarPacientes() {
        return pacientes;
    }
    public static void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }
    public Object buscarDTO(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarDTO'");
    }
    public void atualizar(Long id, PacienteDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
    public void excluir(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }
}

