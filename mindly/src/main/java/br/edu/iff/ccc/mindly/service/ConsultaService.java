package br.edu.iff.ccc.mindly.service;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.mindly.dto.ConsultaDTO;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ConsultaService {

    private final static Map<Long, ConsultaDTO> consultas = new HashMap<>();
    private final static AtomicLong idCounter = new AtomicLong(1);

    public static List<ConsultaDTO> listarTodas() {
        return new ArrayList<>(consultas.values());
    }

    public ConsultaDTO buscarPorId(Long id) {
        return consultas.get(id);
    }

    public static ConsultaDTO salvar(ConsultaDTO dto) {
        if (dto.getId() == null) {
            dto.setId(idCounter.getAndIncrement());
        }
        consultas.put((Long) dto.getId(), dto);
        return dto;
    }

    public void deletar(Long id) {
        consultas.remove(id);
    }
}
