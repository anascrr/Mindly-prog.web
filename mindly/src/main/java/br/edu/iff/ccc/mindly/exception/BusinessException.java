package br.edu.iff.ccc.mindly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma operação viola uma regra de negócio específica do sistema.
 * Por exemplo, tentar agendar uma consulta em um horário indisponível.
 * Por padrão, responde com o status HTTP 400 (Bad Request).
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    /**
     * Construtor que aceita uma mensagem de erro detalhada para ser exibida ao usuário.
     * @param message A mensagem que descreve a regra de negócio violada.
     */
    public BusinessException(String message) {
        super(message);
    }
}