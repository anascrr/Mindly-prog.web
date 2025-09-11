package br.edu.iff.ccc.mindly.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Classe "guardiã" que intercepta exceções lançadas por qualquer @Controller da aplicação.
 * Decide qual resposta (página de erro, redirecionamento) dar para cada tipo de exceção.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // É uma boa prática ter um logger para registrar os erros no console
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Captura exceções de recurso não encontrado (404).
     * Retorna uma página de erro 404 personalizada.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        logger.error("ResourceNotFoundException: {}", ex.getMessage());
        ModelAndView mav = new ModelAndView("error/404"); // Aponta para o arquivo templates/error/404.html
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("requestUri", request.getRequestURI());
        return mav;
    }

    /**
     * Captura exceções de regras de negócio (400).
     * Redireciona o usuário de volta para a página anterior com uma mensagem de erro amigável.
     * Esta abordagem é melhor para formulários, pois não leva o usuário para uma página de erro separada.
     */
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        logger.warn("BusinessException: {}", ex.getMessage());
        // Adiciona a mensagem de erro como um "flash attribute", que sobrevive ao redirecionamento.
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        
        // Redireciona de volta para a URL de onde a requisição veio.
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }

    /**
     * Captura todas as outras exceções não tratadas (erros genéricos 500).
     * Retorna uma página de erro 500 genérica para não expor detalhes do sistema.
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex, HttpServletRequest request) {
        // Loga o erro completo no console para depuração
        logger.error("Erro inesperado ({}): {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        
        ModelAndView mav = new ModelAndView("error/error"); // Aponta para o arquivo templates/error/error.html
        mav.addObject("errorMessage", "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        mav.addObject("requestUri", request.getRequestURI());
        return mav;
    }
}