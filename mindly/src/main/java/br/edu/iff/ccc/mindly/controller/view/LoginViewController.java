package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.mindly.entities.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/api")
public class LoginViewController {

    @GetMapping("/login")
    public String mostrarPaginaLogin() {
        return "login"; // templates/login.html
    }

    @GetMapping("/index")
    public String mostrarIndex() {
        return "index";
    }

    @PostMapping("/login")
    public String login(Login request) {
        return "redirect:/api/index";
    }
}