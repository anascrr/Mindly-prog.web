package br.edu.iff.ccc.mindly.controller.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.iff.ccc.mindly.service.PacienteService;
import br.edu.iff.ccc.mindly.entities.Paciente;

@RestController
@RequestMapping("api/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    // @Operation(summary = "Obter todos os pacientes cadastrados")
    // @ApiResponses({
    // @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
    // @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    // })
    // @GetMapping
    // public ResponseEntity<List<Paciente>> getAllPacientes() {
    // List<Paciente> pacientes = pacienteService.listarTodos();
    // // if (pacientes.isEmpty()) {
    // return ResponseEntity.notFound().build();
    // }
    // return ResponseEntity.ok(pacientes);
    // }

    @Operation(summary = "Obter todos os pacientes cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        if (pacientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @Operation(summary = "Cadastrar novo paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Paciente> adicionarPaciente(@RequestBody Paciente paciente) {
        pacienteService.adicionarPaciente(paciente);
        return ResponseEntity.status(201).body(paciente);
    }
}
