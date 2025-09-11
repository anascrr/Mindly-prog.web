package br.edu.iff.ccc.mindly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um recurso específico (ex: Paciente, Consulta) não é encontrado no sistema.
 * Por padrão, responde com o status HTTP 404 (Not Found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construtor que aceita uma mensagem de erro detalhada.
     * @param message A mensagem que descreve o erro.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}