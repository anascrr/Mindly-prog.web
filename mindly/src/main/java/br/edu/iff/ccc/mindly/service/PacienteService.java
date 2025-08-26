package br.edu.iff.ccc.mindly.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.ccc.mindly.entities.Paciente;

public class PacienteService {
    public static void salvarPaciente(Paciente paciente) {
    }
    
    public static List<Paciente> pacientes = new ArrayList<>(); {
    }
    public static List<Paciente> listarPacientes() {
        return pacientes;
    }
    public static void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }
}

