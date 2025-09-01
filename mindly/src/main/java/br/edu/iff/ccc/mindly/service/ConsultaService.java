package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.entities.Consulta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private Long proximoId = 1L;
    private List<Consulta> consultas = new ArrayList<>();

    public List<Consulta> listarConsultas() {
        return consultas;
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultas.stream()
                .filter(consulta -> consulta.getId().equals(id))
                .findFirst();
    }

    public Consulta salvar(Consulta consulta) {
        if (consulta.getId() == null) {
            consulta.setId(proximoId++);
        } else {
            // Remove a consulta antiga com o mesmo ID, se existir
            consultas.removeIf(c -> c.getId().equals(consulta.getId()));
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
            return consulta;
        }
        return null;
    }
}