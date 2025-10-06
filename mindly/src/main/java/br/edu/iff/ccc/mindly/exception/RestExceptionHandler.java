// src/main/java/br/edu/iff/ccc/mindly/exception/RestExceptionHandler.java
package br.edu.iff.ccc.mindly.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException; // Para validação de DTO
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice; // Use RestControllerAdvice
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

// Este handler só atende controllers em /api/v1/usuarios (ou outros prefixos REST)
@RestControllerAdvice(basePackages = "br.edu.iff.ccc.mindly.controller.restapi")
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    // Handler para erros de validação de DTOs (@Valid @RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        logger.warn("Erro de validação REST na requisição: {}", ex.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Erro de Validação");
        problemDetail.setDetail("Um ou mais campos da requisição são inválidos.");
        // Opcional: define um tipo para o problema, útil para clientes que precisam de mais contexto
        problemDetail.setType(URI.create(request.getRequestURI()));

        // Mapeia os erros de campo para um formato mais legível
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        problemDetail.setProperty("fieldErrors", errors);

        return problemDetail;
    }

    // Handler para recursos não encontrados (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        logger.warn("Recurso não encontrado REST: {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Recurso Não Encontrado");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setType(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    // Handler para violações de regras de negócio (400)
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleBusinessException(BusinessException ex, HttpServletRequest request) {
        logger.warn("Violação de regra de negócio REST: {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Erro de Negócio");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setType(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    // Handler para acesso proibido (403)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ProblemDetail handleForbiddenException(ForbiddenException ex, HttpServletRequest request) {
        logger.warn("Acesso proibido REST: {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problemDetail.setTitle("Acesso Proibido");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setType(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    // Handler para credenciais inválidas (401 - Unauthorized para REST)
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401 para API REST
    public ProblemDetail handleInvalidCredentials(InvalidCredentialsException ex, HttpServletRequest request) {
        logger.warn("Tentativa de login falhou REST: {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("Credenciais Inválidas");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setType(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    // Handler genérico para qualquer outra exceção não tratada (500)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("Erro inesperado REST ({}): {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Erro Interno do Servidor");
        problemDetail.setDetail("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        problemDetail.setType(URI.create(request.getRequestURI()));
        problemDetail.setProperty("exceptionType", ex.getClass().getSimpleName()); // Mais descritivo
        return problemDetail;
    }
}