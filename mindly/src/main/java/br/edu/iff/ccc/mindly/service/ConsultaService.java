package br.edu.iff.ccc.mindly.service;

import br.edu.iff.ccc.mindly.dto.ConsultaDTO;
import br.edu.iff.ccc.mindly.entities.Consulta;
import jakarta.validation.Valid;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private static Long proximoId = 1L;

    private static List<Consulta> consultas = new ArrayList<>();
    public static List<Consulta> listarConsultas() {
        return consultas;
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

    public Consulta atualizar(Long id, ConsultaDTO dto) {
        Optional<Consulta> existente = buscarPorId(id);
        if (existente.isPresent()) {
            Consulta consulta = existente.get();
            consulta.setDataConsulta(dto.getDataConsulta());
            consulta.setObservacao(dto.getObservacao());
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

    public static void adicionarConsulta(ConsultaDTO dto) {
        dto.setId(proximoId++);
        consultas.add(dto);
    }

    public void cancelar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelar'");
    }

}