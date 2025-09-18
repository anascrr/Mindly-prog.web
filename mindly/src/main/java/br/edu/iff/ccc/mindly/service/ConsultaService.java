package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.dto.ConsultaRequestDTO;
import br.edu.iff.ccc.mindly.dto.ConsultaResponseDTO;
import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.exception.BusinessException;
import br.edu.iff.ccc.mindly.exception.ResourceNotFoundException;
import br.edu.iff.ccc.mindly.repository.ConsultaRepository;
import br.edu.iff.ccc.mindly.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public ConsultaResponseDTO criarConsulta(ConsultaRequestDTO dto) {
        validarAgendamento(dto.getDataConsulta(), dto.getMedico(), null);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + dto.getPacienteId()));
        Consulta novaConsulta = new Consulta();
        novaConsulta.setPaciente(paciente);
        novaConsulta.setMedico(dto.getMedico());
        novaConsulta.setDataConsulta(dto.getDataConsulta());
        novaConsulta.setObservacao(dto.getObservacao());
        Consulta consultaSalva = consultaRepository.save(novaConsulta);
        return ConsultaResponseDTO.fromEntity(consultaSalva);
    }

    public ConsultaResponseDTO atualizarConsulta(Long id, ConsultaRequestDTO dto) {
        Consulta consultaExistente = buscarConsultaPorId(id);
        validarAgendamento(dto.getDataConsulta(), dto.getMedico(), id);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + dto.getPacienteId()));
        consultaExistente.setDataConsulta(dto.getDataConsulta());
        consultaExistente.setObservacao(dto.getObservacao());
        consultaExistente.setMedico(dto.getMedico());
        consultaExistente.setPaciente(paciente);
        Consulta consultaAtualizada = consultaRepository.save(consultaExistente);
        return ConsultaResponseDTO.fromEntity(consultaAtualizada);
    }

    public List<ConsultaResponseDTO> listarTodasDTO() {
        return consultaRepository.findAll().stream().map(ConsultaResponseDTO::fromEntity).collect(Collectors.toList());
    }

    public ConsultaResponseDTO buscarPorIdDTO(Long id) {
        Consulta consulta = buscarConsultaPorId(id);
        return ConsultaResponseDTO.fromEntity(consulta);
    }

    public void remover(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta não encontrada com o ID: " + id);
        }
        consultaRepository.deleteById(id);
    }

    public List<Consulta> listarTodasConsultasEntidade() {
        return consultaRepository.findAll();
    }
    
    public Consulta buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com o ID: " + id));
    }

    // MÉTODO QUE ESTAVA FALTANDO
    public List<Consulta> obterConsultasPorData(LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.plusDays(1).atStartOfDay();
        return consultaRepository.findByDataConsultaBetween(inicio, fim);
    }

    private void validarAgendamento(LocalDateTime dataConsulta, String medico, Long consultaIdExistente) {
        if (dataConsulta != null && dataConsulta.isBefore(LocalDateTime.now())) {
            throw new BusinessException("Não é possível agendar consultas em datas ou horários passados.");
        }
        List<Consulta> consultasNoMesmoHorario = consultaRepository.findByMedicoAndDataConsulta(medico, dataConsulta);
        if (consultaIdExistente == null) {
            if (!consultasNoMesmoHorario.isEmpty()) {
                throw new BusinessException("Este horário já está agendado para o médico selecionado.");
            }
        } else {
            if (!consultasNoMesmoHorario.isEmpty() && !consultasNoMesmoHorario.get(0).getId().equals(consultaIdExistente)) {
                 throw new BusinessException("Este horário já está agendido para o médico selecionado.");
            }
        }
    }
}