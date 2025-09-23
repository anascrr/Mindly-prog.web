package br.edu.iff.ccc.mindly.controller.restapi;

import br.edu.iff.ccc.mindly.dto.PacienteRequestDTO;
import br.edu.iff.ccc.mindly.dto.PacienteResponseDTO;
import br.edu.iff.ccc.mindly.dto.PacienteUpdateDTO;
import br.edu.iff.ccc.mindly.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pacientes")
public class PacienteRestController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    @Operation(summary = "Obter todos os pacientes")
    public ResponseEntity<List<PacienteResponseDTO>> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.listarTodosDTO());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um paciente por ID")
    public ResponseEntity<PacienteResponseDTO> getPacienteById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorIdDTO(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo paciente")
    public ResponseEntity<PacienteResponseDTO> adicionarPaciente(@RequestBody @Valid PacienteRequestDTO dto) {
        PacienteResponseDTO novoPacienteDTO = pacienteService.criarPaciente(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoPacienteDTO.getId()).toUri();
        return ResponseEntity.created(location).body(novoPacienteDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um paciente existente")
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid PacienteUpdateDTO dto) {
        return ResponseEntity.ok(pacienteService.atualizarPaciente(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um paciente")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
        return ResponseEntity.noContent().build();
    }
}