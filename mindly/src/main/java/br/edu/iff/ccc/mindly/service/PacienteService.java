package br.edu.iff.ccc.mindly.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.ccc.mindly.entities.Paciente;

public class PacienteService {
    public static void salvarPaciente(Paciente paciente) {
    }
    
    public Paciente buscarPacientePorId(Long id) {
        Paciente paciente = new Paciente();
        if (id == null) {
            throw new IllegalArgumentException("ID do paciente não pode ser nulo");
        }

        paciente.setId(id);
        paciente.setNome("Joazinho");
        paciente.setEmail("joazinho@gmail.com");
        paciente.setTelefone("123456789");
        paciente.setEndereco("Rua Exemplo, 123");
        paciente.setDataNascimento("01/01/2000");
        paciente.setCpf("12345678901");
        paciente.setPlanoSaude("Plano Exemplo");
        return paciente;
        


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

