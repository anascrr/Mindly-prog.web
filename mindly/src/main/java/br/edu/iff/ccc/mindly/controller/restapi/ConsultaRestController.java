package br.edu.iff.ccc.mindly.controller.restapi;

import br.edu.iff.ccc.mindly.dto.ConsultaRequestDTO;
import br.edu.iff.ccc.mindly.dto.ConsultaResponseDTO;
import br.edu.iff.ccc.mindly.service.ConsultaService;
// Importações do Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
// Outras importações
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired; // Mantendo @Autowired por enquanto
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/consultas")
@Tag(name = "Consultas", description = "Operações relacionadas a agendamento e gerenciamento de consultas") // Tag para organizar no Swagger UI
public class ConsultaRestController {

    @Autowired // Mantendo Autowired, mas injeção por construtor é preferível
    private ConsultaService consultaService;

    @GetMapping
    @Operation(summary = "Listar todas as consultas",
               description = "Retorna uma lista de todas as consultas agendadas.")
    @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso",
                 content = @Content(schema = @Schema(implementation = ConsultaResponseDTO[].class))) // Lista de ConsultaResponseDTO
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se o endpoint for protegido)")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<ConsultaResponseDTO>> getAllConsultas() {
        return ResponseEntity.ok(consultaService.listarTodasDTO());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID",
               description = "Retorna os detalhes de uma consulta específica pelo seu ID.")
    @Parameter(name = "id", description = "ID único da consulta a ser buscada", example = "1")
    @ApiResponse(responseCode = "200", description = "Consulta encontrada",
                 content = @Content(schema = @Schema(implementation = ConsultaResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada") // Se o service lançar ResourceNotFoundException
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se o endpoint for protegido)")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<ConsultaResponseDTO> getConsultaByID(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorIdDTO(id));
    }

    @PostMapping
    @Operation(summary = "Agendar nova consulta",
               description = "Agenda uma nova consulta no sistema. O ID do paciente é necessário, e o nome do médico é fornecido como string.")
    @ApiResponse(responseCode = "201", description = "Consulta agendada com sucesso",
                 content = @Content(schema = @Schema(implementation = ConsultaResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida (e.g., validação falhou, paciente não encontrado, horário indisponível)") // Erros de validação ou BusinessException
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se o endpoint for protegido)")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<ConsultaResponseDTO> newConsulta(@RequestBody @Valid ConsultaRequestDTO dto) {
        ConsultaResponseDTO novaConsulta = consultaService.criarConsulta(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaConsulta.id()).toUri(); // Use novaConsulta.id() se for um record
        return ResponseEntity.created(location).body(novaConsulta); // 201 Created
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar consulta existente",
               description = "Atualiza os dados de uma consulta agendada pelo seu ID. O ID do paciente e o nome do médico são fornecidos.")
    @Parameter(name = "id", description = "ID único da consulta a ser atualizada", example = "1")
    @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso",
                 content = @Content(schema = @Schema(implementation = ConsultaResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida (e.g., validação falhou, paciente não encontrado, horário indisponível)")
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se o endpoint for protegido)")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada") // Se o service lançar ResourceNotFoundException
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<ConsultaResponseDTO> updateConsulta(
            @PathVariable Long id,
            @RequestBody @Valid ConsultaRequestDTO dto) {
        return ResponseEntity.ok(consultaService.atualizarConsulta(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir consulta",
               description = "Exclui uma consulta agendada pelo seu ID. **Pode requerer autenticação.**")
    @Parameter(name = "id", description = "ID único da consulta a ser excluída", example = "1")
    @ApiResponse(responseCode = "204", description = "Consulta excluída com sucesso (No Content)")
    @ApiResponse(responseCode = "403", description = "Acesso proibido (se o endpoint for protegido)")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada") // Se o service lançar ResourceNotFoundException
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        consultaService.remover(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}