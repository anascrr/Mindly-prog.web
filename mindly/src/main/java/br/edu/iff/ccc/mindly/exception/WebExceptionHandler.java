// src/main/java/br/edu/iff/ccc/mindly/exception/WebExceptionHandler.java
package br.edu.iff.ccc.mindly.exception;

import br.edu.iff.ccc.mindly.dto.UsuarioLoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice; // Use ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Este handler atende controllers fora do pacote REST API (ou para todos, se basePackages não for especificado)
// Idealmente, você apontaria para o pacote dos seus controllers MVC
@ControllerAdvice // Sem basePackages para ser mais abrangente para Views, ou aponte para seu pacote MVC
public class WebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleInvalidCredentials(InvalidCredentialsException ex, Model model) {
        logger.warn("Tentativa de login falhou (MVC): {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("usuarioLoginDTO", new UsuarioLoginDTO());
        return "login";
    }

    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException ex, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        logger.warn("Violação de regra de negócio (MVC): {}", ex.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        logger.error("Recurso não encontrado (MVC): {}", ex.getMessage());
        ModelAndView mav = new ModelAndView("error/404");
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("requestUri", request.getRequestURI());
        mav.setStatus(HttpStatus.NOT_FOUND); // Definir status HTTP para 404
        return mav;
    }

    // Handler genérico para MVC - retornará uma página de erro padrão
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("Erro inesperado (MVC) ({}): {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        ModelAndView mav = new ModelAndView("error/500"); // Crie uma página error/500.html
        mav.addObject("errorMessage", "Ocorreu um erro inesperado.");
        mav.addObject("requestUri", request.getRequestURI());
        mav.addObject("exceptionType", ex.getClass().getSimpleName());
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); // Definir status HTTP para 500
        return mav;
    }
}