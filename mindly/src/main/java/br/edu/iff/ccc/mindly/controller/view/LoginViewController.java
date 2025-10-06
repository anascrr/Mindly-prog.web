package br.edu.iff.ccc.mindly.controller.view;

import br.edu.iff.ccc.mindly.dto.UsuarioLoginDTO;
import br.edu.iff.ccc.mindly.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginViewController {

    @Autowired
    private AuthService authService;

    @GetMapping({"/", "/login"})
    public String mostrarLogin(Model model) {
        model.addAttribute("loginDTO", new UsuarioLoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@ModelAttribute("loginDTO") UsuarioLoginDTO dto) {
        // A validação acontece aqui. Se as credenciais estiverem erradas,
        // o AuthService vai lançar a exceção e o GlobalExceptionHandler vai interceptar,
        // redirecionando de volta para a página de login com a mensagem de erro.
        authService.autenticar(dto);

        return "redirect:/home";
    }
}