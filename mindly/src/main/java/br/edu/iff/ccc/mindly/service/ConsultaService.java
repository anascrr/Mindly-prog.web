package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.entities.Consulta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final List<Consulta> consultas = new ArrayList<>();
    private Long proximoId = 1L;

    public List<Consulta> listarTodas() {
        return new ArrayList<>(consultas);
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultas.stream()
                .filter(consulta -> consulta.getId().equals(id))
                .findFirst();
    }

    public Consulta salvar(Consulta consulta) {
        if (consulta.getId() == null) {
            consulta.setId(gerarNovoId());
        }
        consultas.add(consulta);
        return consulta;
    }

    public boolean remover(Long id) {
        return consultas.removeIf(consulta -> consulta.getId().equals(id));
    }

    public Consulta atualizar(Long id, Consulta novaConsulta) {
        Optional<Consulta> existente = buscarPorId(id);
        if (existente.isPresent()) {
            Consulta consulta = existente.get();
            consulta.setDataConsulta(novaConsulta.getDataConsulta());
            consulta.setObservacao(novaConsulta.getObservacao());
            // Atualize outros campos conforme necessário
            return consulta;
        }
        return null;
    }

    private Long gerarNovoId() {
        return consultas.stream()
                .mapToLong(Consulta::getId)
                .max()
                .orElse(0L) + 1;
    }

    public void adicionarConsulta(Consulta consulta) {
        consulta.setId(proximoId++);
        consultas.add(consulta);
    }
}