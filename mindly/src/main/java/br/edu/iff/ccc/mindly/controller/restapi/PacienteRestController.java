package br.edu.iff.ccc.mindly.controller.restapi;

import br.edu.iff.ccc.mindly.dto.PacienteRequestDTO;
import br.edu.iff.ccc.mindly.dto.PacienteResponseDTO;
import br.edu.iff.ccc.mindly.dto.PacienteUpdateDTO;
import br.edu.iff.ccc.mindly.service.PacienteService;
// Importações do Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
// Outras importações
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pacientes")
@Tag(name = "Pacientes", description = "Operações relacionadas a pacientes") // Tag para organizar no Swagger UI
public class PacienteRestController {

   
    private final PacienteService pacienteService;

    public PacienteRestController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os pacientes",
               description = "Retorna uma lista de todos os pacientes cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso",
                 content = @Content(schema = @Schema(implementation = PacienteResponseDTO[].class))) // Retorna uma lista de PacienteResponseDTO
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se houver autenticação de ADMIN aqui)") // Adicionar se este endpoint for protegido
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor") // Genérico para qualquer exceção não tratada
    public ResponseEntity<List<PacienteResponseDTO>> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.listarTodosDTO());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID",
               description = "Retorna os detalhes de um paciente específico pelo seu ID.")
    @Parameter(name = "id", description = "ID único do paciente a ser buscado", example = "1111111") // Documenta o PathVariable
    @ApiResponse(responseCode = "200", description = "Paciente encontrado",
                 content = @Content(schema = @Schema(implementation = PacienteResponseDTO.class))) // Retorna PacienteResponseDTO
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado") // Se o service lançar ResourceNotFoundException
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se houver autenticação aqui)")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<PacienteResponseDTO> getPacienteById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorIdDTO(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo paciente",
               description = "Cria um novo registro de paciente no sistema.")
    @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso",
                 content = @Content(schema = @Schema(implementation = PacienteResponseDTO.class))) // Retorna PacienteResponseDTO após criação
    @ApiResponse(responseCode = "400", description = "Requisição inválida (e.g., validação falhou, dados duplicados)") // Se o RequestDTO falhar
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se este endpoint for protegido por ADMIN)")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<PacienteResponseDTO> adicionarPaciente(@RequestBody @Valid PacienteRequestDTO dto) {
        PacienteResponseDTO novoPacienteDTO = pacienteService.criarPaciente(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoPacienteDTO.getId()).toUri();
        return ResponseEntity.created(location).body(novoPacienteDTO); // 201 Created
    }

    @PutMapping("/{id}")
@Operation(summary = "Atualizar paciente existente",
           description = "Atualiza os dados de um paciente existente pelo seu ID.")
@Parameter(name = "id", description = "ID único do paciente a ser atualizado", example = "1")
@ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso",
             content = @Content(schema = @Schema(implementation = PacienteResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida (e.g., validação falhou, dados inconsistentes)")
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se este endpoint for protegido por ADMIN)")
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado") // Se o service lançar ResourceNotFoundException
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
   public ResponseEntity<PacienteResponseDTO> atualizarPaciente(@PathVariable Long id, @RequestBody @Valid PacienteUpdateDTO dto) {
    return ResponseEntity.ok(pacienteService.atualizarPaciente(id, dto));
}

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir paciente",
               description = "Exclui um paciente existente pelo seu ID.")
    @Parameter(name = "id", description = "ID único do paciente a ser excluído", example = "1111111") // Documenta o PathVariable
    @ApiResponse(responseCode = "204", description = "Paciente excluído com sucesso (No Content)") // 204 é padrão para DELETE
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se este endpoint for protegido)")
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado") // Se o service lançar ResourceNotFoundException
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}