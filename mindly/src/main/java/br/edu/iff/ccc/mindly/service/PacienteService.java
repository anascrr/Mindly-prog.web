package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;
// import br.edu.iff.ccc.mindly.dto.PacienteDTO;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.repository.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public void adicionarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public void atualizarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = buscarPorId(pacienteDTO.getId());
        if (paciente != null) {
            paciente.setNome(pacienteDTO.getNome());
            paciente.setEmail(pacienteDTO.getEmail());
            paciente.setTelefone(pacienteDTO.getTelefone());
            paciente.setEndereco(pacienteDTO.getEndereco());
            paciente.setDataNascimento(pacienteDTO.getDataNascimento());
            paciente.setCpf(pacienteDTO.getCpf());
            paciente.setPlanoSaude(pacienteDTO.getPlanoSaude());
            pacienteRepository.save(paciente);
        }
    }

    public void excluirPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

}