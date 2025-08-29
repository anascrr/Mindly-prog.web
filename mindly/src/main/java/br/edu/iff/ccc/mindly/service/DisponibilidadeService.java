// DisponibilidadeService.java
package br.edu.iff.ccc.mindly.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DisponibilidadeService {
    private final DisponibilidadeRepository repo;

    public DisponibilidadeService(DisponibilidadeRepository repo) {
        this.repo = repo;
    }

    public List<Disponibilidade> listarTodas() { return repo.findAll(); }

    public List<Disponibilidade> listarDisponiveis() { return repo.findByReservadaFalse(); }

    public DisponibilidadeDTO buscarDTO(Long id) {
        return DisponibilidadeDTO.fromEntity(repo.findById(id).orElseThrow());
    }

    @Transactional
    public Disponibilidade salvar(DisponibilidadeDTO dto) {
        return repo.save(dto.toEntity());
    }

    @Transactional
    public Disponibilidade atualizar(Long id, DisponibilidadeDTO dto) {
        var d = repo.findById(id).orElseThrow();
        d.setProfissional(dto.getProfissional());
        d.setInicio(dto.getInicio());
        d.setFim(dto.getFim());
        d.setSala(dto.getSala());
        return repo.save(d);
    }

    @Transactional
    public void excluir(Long id) { repo.deleteById(id); }
}
