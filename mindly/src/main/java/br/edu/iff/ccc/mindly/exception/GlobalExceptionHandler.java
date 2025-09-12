package br.edu.iff.ccc.mindly.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.iff.ccc.mindly.dto.LoginDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleInvalidCredentials(InvalidCredentialsException ex, Model model) {
        model.addAttribute("erro", ex.getMessage());
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("erro", ex.getMessage());
        return "error/404";
    }
}
