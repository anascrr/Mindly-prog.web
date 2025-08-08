package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(path = "principal")
public class  LoginViewController {

    @GetMapping("/login")
    public String mostrarPaginaLogin() {
        return "login"; // templates/login.html
    }
}
