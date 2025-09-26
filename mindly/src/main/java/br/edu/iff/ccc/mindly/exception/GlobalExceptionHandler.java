// src/main/java/br/edu/iff/ccc/mindly/exception/GlobalExceptionHandler.java
package br.edu.iff.ccc.mindly.exception;

import br.edu.iff.ccc.mindly.dto.UsuarioLoginDTO; // CORRIGIDO: Usa UsuarioLoginDTO
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Captura exceções de credenciais inválidas.
    // Retorna o usuário para a tela de login com uma mensagem de erro.
    @ExceptionHandler(InvalidCredentialsException.class) // Mantém InvalidCredentialsException
    public String handleInvalidCredentials(InvalidCredentialsException ex, Model model) {
        logger.warn("Tentativa de login falhou: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage()); // Muda para errorMessage para consistência com LoginViewController
        model.addAttribute("usuarioLoginDTO", new UsuarioLoginDTO()); // CORRIGIDO: Usa UsuarioLoginDTO
        return "login";
    }

    // Captura exceções de regras de negócio (ex: agendamento inválido).
    // Redireciona o usuário de volta para a página anterior (o formulário) com a
    // mensagem de erro.
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException ex, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        logger.warn("Violação de regra de negócio: {}", ex.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }

    // MELHORADO: Captura exceções de recurso não encontrado (404).
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        logger.error("Recurso não encontrado: {}", ex.getMessage());
        ModelAndView mav = new ModelAndView("error/404");
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("requestUri", request.getRequestURI());
        return mav;
    }

    // Captura todas as outras exceções não tratadas (erros genéricos 500).
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ProblemDetail handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("Erro inesperado ({}): {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Erro inesperado");
        problemDetail.setDetail("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        problemDetail.setProperty("exception", ex.getClass().getSimpleName());
        problemDetail.setProperty("requestUri", request.getRequestURI());
        return problemDetail;
    }
}