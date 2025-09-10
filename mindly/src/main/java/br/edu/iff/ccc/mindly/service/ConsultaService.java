package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void remover(Long id) {
        consultaRepository.deleteById(id);
    }

    public Consulta atualizar(Long id, Consulta novaConsulta) {
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setDataConsulta(novaConsulta.getDataConsulta());
            consulta.setObservacao(novaConsulta.getObservacao());
            consulta.setMedico(novaConsulta.getMedico());
            consulta.setPaciente(novaConsulta.getPaciente());
            return consultaRepository.save(consulta);
        }).orElse(null);
    }

    public List<Consulta> obterConsultasPorData(LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.plusDays(1).atStartOfDay();
        return consultaRepository.findByDataConsultaBetween(inicio, fim);
    }
}
