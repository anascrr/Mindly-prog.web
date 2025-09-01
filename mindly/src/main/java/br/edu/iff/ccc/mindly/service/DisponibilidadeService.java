package br.edu.iff.ccc.mindly.service;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.mindly.dto.DisponibilidadeDTO;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DisponibilidadeService {

    private final Map<Long, DisponibilidadeDTO> disponibilidades = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<DisponibilidadeDTO> listarTodas() {
        return new ArrayList<>(disponibilidades.values());
    }

    public DisponibilidadeDTO buscarPorId(Long id) {
        return disponibilidades.get(id);
    }

    public DisponibilidadeDTO salvar(DisponibilidadeDTO dto) {
        if (dto.getId() == null) {
            dto.setId(idCounter.getAndIncrement());
        }
        disponibilidades.put(dto.getId(), dto);
        return dto;
    }

    public void deletar(Long id) {
        disponibilidades.remove(id);
    }
}
