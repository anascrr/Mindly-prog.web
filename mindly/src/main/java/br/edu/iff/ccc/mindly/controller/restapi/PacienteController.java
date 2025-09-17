package br.edu.iff.ccc.mindly.controller.restapi;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.iff.ccc.mindly.service.PacienteService;
import br.edu.iff.ccc.mindly.entities.Paciente;

@RestController
@RequestMapping(path = "api/v1/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Obter todos os pacientes cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paciente encontrado")
    })
    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        // O Spring automaticamente retornará um array JSON vazio se a lista estiver
        // vazia.
        return ResponseEntity.ok(pacientes);

    }

    @Operation(summary = "Cadastrar novo paciente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
   @PostMapping
public ResponseEntity<Paciente> adicionarPaciente(@RequestBody Paciente paciente) {
    Paciente novoPaciente = pacienteService.adicionarPaciente(paciente); // Recebe o paciente salvo

    // Constrói a URI para o novo recurso. Ex: http://localhost:8080/api/v1/pacientes/15
    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest() // Pega a URL base (api/v1/pacientes)
            .path("/{id}")       // Adiciona /id
            .buildAndExpand(novoPaciente.getId()) // Substitui {id} pelo ID do novo paciente
            .toUri();

    // Retorna 201 Created com a URI no cabeçalho Location e o objeto no corpo
    return ResponseEntity.created(location).body(novoPaciente);
}
}
