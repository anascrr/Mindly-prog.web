package br.edu.iff.ccc.mindly.controller.restapi;

import br.edu.iff.ccc.mindly.dto.ConsultaRequestDTO;
import br.edu.iff.ccc.mindly.dto.ConsultaResponseDTO;
import br.edu.iff.ccc.mindly.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
// ajustar front end, testar TUDOOO, arrumar documentação

@RestController
@RequestMapping(path = "api/v1/consultas")
public class ConsultaRestController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    @Operation(summary = "Listar todas as consultas")
    public ResponseEntity<List<ConsultaResponseDTO>> getAllConsultas() {
        return ResponseEntity.ok(consultaService.listarTodasDTO());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID")
    public ResponseEntity<ConsultaResponseDTO> getConsultaByID(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorIdDTO(id));
    }

    @PostMapping
    @Operation(summary = "Agendar nova consulta")
    public ResponseEntity<ConsultaResponseDTO> newConsulta(@RequestBody @Valid ConsultaRequestDTO dto) {
        ConsultaResponseDTO novaConsulta = consultaService.criarConsulta(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaConsulta.id()).toUri();
        return ResponseEntity.created(location).body(novaConsulta);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar consulta existente")
    public ResponseEntity<ConsultaResponseDTO> updateConsulta(@PathVariable Long id,
            @RequestBody @Valid ConsultaRequestDTO dto) {
        return ResponseEntity.ok(consultaService.atualizarConsulta(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir consulta")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        consultaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
