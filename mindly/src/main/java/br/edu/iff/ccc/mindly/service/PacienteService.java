package br.edu.iff.ccc.mindly.service;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PacienteService {

    private final Map<Long, PacienteDTO> pacientes = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<PacienteDTO> listarTodos() {
        return new ArrayList<>(pacientes.values());
    }

    public PacienteDTO buscarPorId(Long id) {
        return pacientes.get(id);
    }

    public PacienteDTO salvar(PacienteDTO dto) {
        if (dto.getId() == null) {
            dto.setId(idCounter.getAndIncrement());
        }
        pacientes.put(dto.getId(), dto);
        return dto;
    }

    public void deletar(Long id) {
        pacientes.remove(id);
    }
}
