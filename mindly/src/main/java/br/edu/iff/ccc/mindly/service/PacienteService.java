package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.dto.PacienteRequestDTO;
import br.edu.iff.ccc.mindly.dto.PacienteResponseDTO;
import br.edu.iff.ccc.mindly.dto.PacienteUpdateDTO;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.exception.BusinessException;
import br.edu.iff.ccc.mindly.exception.ResourceNotFoundException;
import br.edu.iff.ccc.mindly.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    // --- MÉTODOS PARA A LÓGICA DE NEGÓCIO E APIs ---

    public PacienteResponseDTO criarPaciente(PacienteRequestDTO dto) {
        if (pacienteRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new BusinessException("CPF já cadastrado no sistema.");
        }
        if (pacienteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessException("E-mail já cadastrado no sistema.");
        }

        Paciente paciente = dto.toEntity();
        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        return new PacienteResponseDTO(pacienteSalvo);
    }

    public PacienteResponseDTO atualizarPaciente(Long id, PacienteUpdateDTO dto) {
        Paciente pacienteExistente = buscarPacientePorId(id);

        pacienteRepository.findByCpf(dto.getCpf()).ifPresent(paciente -> {
            if (!paciente.getId().equals(id)) {
                throw new BusinessException("CPF já cadastrado para outro paciente.");
            }
        });
        pacienteRepository.findByEmail(dto.getEmail()).ifPresent(paciente -> {
            if (!paciente.getId().equals(id)) {
                throw new BusinessException("E-mail já cadastrado para outro paciente.");
            }
        });

        pacienteExistente.setNome(dto.getNome());
        pacienteExistente.setEmail(dto.getEmail());
        pacienteExistente.setTelefone(dto.getTelefone());
        pacienteExistente.setEndereco(dto.getEndereco());
        pacienteExistente.setDataNascimento(dto.getDataNascimento());
        pacienteExistente.setCpf(dto.getCpf());
        pacienteExistente.setPlanoSaude(dto.getPlanoSaude());

        Paciente pacienteAtualizado = pacienteRepository.save(pacienteExistente);
        return new PacienteResponseDTO(pacienteAtualizado);
    }

    public void excluirPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
    
    // --- MÉTODOS DE CONSULTA (para API e Views) ---

    public List<PacienteResponseDTO> listarTodosDTO() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PacienteResponseDTO buscarPorIdDTO(Long id) {
        Paciente paciente = buscarPacientePorId(id);
        return new PacienteResponseDTO(paciente);
    }
    
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
    
    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + id));
    }
}