package br.edu.iff.ccc.mindly.controller.restapi;

import br.edu.iff.ccc.mindly.dto.UsuarioLoginDTO;
import br.edu.iff.ccc.mindly.dto.UsuarioCadastroDTO;
import br.edu.iff.ccc.mindly.dto.UsuarioResponseDTO;
import br.edu.iff.ccc.mindly.entities.Usuario;
import br.edu.iff.ccc.mindly.exception.BusinessException;
import br.edu.iff.ccc.mindly.exception.ForbiddenException;
import br.edu.iff.ccc.mindly.exception.ResourceNotFoundException;
import br.edu.iff.ccc.mindly.service.AuthService;
import br.edu.iff.ccc.mindly.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioRestController {

    // Injeção via construtor (melhor prática)
    private final UsuarioService usuarioService;
    private final AuthService authService;

    public UsuarioRestController(UsuarioService usuarioService, AuthService authService) {
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    // --- Exception Handlers ---
    // Mantido como ResponseEntity<String> conforme seu código
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
    // -------------------------------------------------------------------

    @PostMapping("/autenticar")
    @Operation(summary = "Autenticar usuário (Login)",
               description = "Autentica um usuário e retorna seus dados básicos se as credenciais forem válidas.")
    @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida",
                 content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))) // Retorna UsuarioResponseDTO
    @ApiResponse(responseCode = "400", description = "Credenciais inválidas ou erro de validação",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    public ResponseEntity<UsuarioResponseDTO> autenticar(@Valid @RequestBody UsuarioLoginDTO loginDto) {
        // Chama o AuthService
        Usuario usuarioAutenticado = authService.autenticar(loginDto);

        // Converte a entidade Usuario para UsuarioResponseDTO para o retorno
        return ResponseEntity.ok(new UsuarioResponseDTO(usuarioAutenticado)); // 200 OK
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários",
               description = "Retorna uma lista de todos os usuários cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                 content = @Content(schema = @Schema(implementation = UsuarioResponseDTO[].class))) // Array de UsuarioResponseDTO
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios); // 200 OK
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID",
               description = "Retorna os detalhes de um usuário específico pelo seu ID.")
    @Parameter(name = "id", description = "ID único do usuário a ser buscado", example = "3333333") // Exemplo mais simples
    @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                 content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario); // 200 OK
    }

    @PostMapping
    @Operation(summary = "Criar um novo usuário",
               description = "Cria um novo usuário no sistema. **Requer autenticação como ADMIN via cabeçalho.**")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                 content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))) // DTO de resposta
    @ApiResponse(responseCode = "400", description = "Requisição inválida (e.g., email já existe, validação falhou)",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    @ApiResponse(responseCode = "403", description = "Acesso proibido (usuário não é ADMIN ou cabeçalho ausente)",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    @Parameter(
        name = "X-Logged-User-Email", // Consistente com X- prefixo
        in = ParameterIn.HEADER,
        description = "Email do usuário logado (deve ser um ADMIN para esta operação).",
        required = true,
        schema = @Schema(type = "string", format = "email"),
        example = "admin@mindly.com"
    )
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(
            @Valid @RequestBody UsuarioCadastroDTO dto, // Usa UsuarioCadastroDTO como corpo da requisição
            @RequestHeader(name = "X-Logged-User-Email") String emailDoUsuarioLogado) { // Required=true implícito
        UsuarioResponseDTO novoUsuario = usuarioService.criarUsuario(dto, emailDoUsuarioLogado);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoUsuario.getId())
                .toUri();

        return ResponseEntity.created(location).body(novoUsuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário",
               description = "Atualiza os dados de um usuário existente. **Requer autenticação como ADMIN.**")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                 content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida (e.g., validação falhou, email já existe)",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    @ApiResponse(responseCode = "403", description = "Acesso proibido (usuário não é ADMIN ou cabeçalho ausente)",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    @Parameter(name = "id", description = "ID único do usuário a ser atualizado", example = "3333333") // Exemplo mais simples
    @Parameter(
        name = "X-Logged-User-Email", // Consistente com X- prefixo
        in = ParameterIn.HEADER,
        description = "Email do usuário logado (deve ser um ADMIN para esta operação).",
        required = true,
        schema = @Schema(type = "string", format = "email"),
        example = "admin@mindly.com"
    )
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioCadastroDTO dto, // Usa UsuarioCadastroDTO como corpo da requisição
            @RequestHeader(name = "X-Logged-User-Email") String emailDoUsuarioLogado) {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, dto, emailDoUsuarioLogado);
        return ResponseEntity.ok(usuarioAtualizado);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um usuário",
               description = "Remove um usuário do sistema pelo seu ID. **Requer autenticação como ADMIN via cabeçalho.**")
    @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso proibido (usuário não é ADMIN ou cabeçalho ausente)",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                 content = @Content(schema = @Schema(implementation = String.class))) // Exemplo para mensagens de erro
    @Parameter(name = "id", description = "ID único do usuário a ser excluído", example = "3333333") // Exemplo mais simples
    @Parameter(
        name = "X-Logged-User-Email", // Consistente com X- prefixo
        in = ParameterIn.HEADER,
        description = "Email do usuário logado (deve ser um ADMIN para esta operação).",
        required = true,
        schema = @Schema(type = "string", format = "email"),
        example = "admin@mindly.com"
    )
    public ResponseEntity<Void> excluirUsuario(
            @PathVariable Long id,
            @RequestHeader(name = "X-Logged-User-Email") String emailDoUsuarioLogado) {
        usuarioService.excluirUsuario(id, emailDoUsuarioLogado);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content para exclusão bem-sucedida
    }
}