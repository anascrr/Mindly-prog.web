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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    // --- Exception Handlers ---
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
    @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida")
    @ApiResponse(responseCode = "400", description = "Credenciais inválidas ou erro de validação")
    public ResponseEntity<UsuarioResponseDTO> autenticar(@Valid @RequestBody UsuarioLoginDTO loginDto) {
        // Chama o AuthService
        Usuario usuarioAutenticado = authService.autenticar(loginDto);
        
        // Converte a entidade Usuario para UsuarioResponseDTO para o retorno
        return ResponseEntity.ok(new UsuarioResponseDTO(usuarioAutenticado)); // 200 OK
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários",
               description = "Retorna uma lista de todos os usuários cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios); // 200 OK
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID",
               description = "Retorna os detalhes de um usuário específico pelo seu ID.")
    @Parameter(name = "id", description = "ID único do usuário a ser buscado", example = "1111111") // Documenta o PathVariable
    @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                 content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario); // 200 OK
    }

    @PostMapping
    @Operation(summary = "Criar um novo usuário",
               description = "Cria um novo usuário no sistema (apenas Admin).")
    @Parameter(name = "Logged-User-Email", description = "Email do usuário logado realizando a operação (para verificação de ADMIN)", required = false, example = "admin@mindly.com")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida (e.g., email já existe)")
    @ApiResponse(responseCode = "403", description = "Acesso proibido (não é Admin)")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(
            @Valid @RequestBody UsuarioCadastroDTO dto,
            @RequestHeader(name = "Logged-User-Email", required = false) String emailDoUsuarioLogado) {
        UsuarioResponseDTO novoUsuario = usuarioService.criarUsuario(dto, emailDoUsuarioLogado);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoUsuario.getId())
                .toUri();

        return ResponseEntity.created(location).body(novoUsuario); // 201 Created com Location header
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário",
               description = "Atualiza os dados de um usuário existente (apenas Admin).")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "403", description = "Acesso proibido")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioCadastroDTO dto,
            @RequestHeader(name = "X-Logged-User-Email", required = false) String emailDoUsuarioLogado) {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, dto, emailDoUsuarioLogado);
        return ResponseEntity.ok(usuarioAtualizado); // 200 OK
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um usuário",
               description = "Remove um usuário do sistema pelo seu ID (apenas Admin).")
    @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso proibido")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    public ResponseEntity<Void> excluirUsuario(
            @PathVariable Long id,
            @RequestHeader(name = "Logged-User-Email", required = false) String emailDoUsuarioLogado) {
        usuarioService.excluirUsuario(id, emailDoUsuarioLogado);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}