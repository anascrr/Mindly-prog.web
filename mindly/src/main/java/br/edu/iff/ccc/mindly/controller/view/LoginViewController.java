package br.edu.iff.ccc.mindly.controller.view;

import br.edu.iff.ccc.mindly.dto.LoginDTO;
import br.edu.iff.ccc.mindly.entities.Login;
import br.edu.iff.ccc.mindly.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginViewController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/home")
    public String autenticar(@ModelAttribute("loginDTO") LoginDTO dto, Model model) {
        Login usuario = authService.autenticar(dto);

        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "index";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }
    }
}
