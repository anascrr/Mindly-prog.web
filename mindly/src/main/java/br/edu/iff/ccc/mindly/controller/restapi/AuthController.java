package br.edu.iff.ccc.mindly.controller.restapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api")
public class AuthController {

    @GetMapping("/login")
    public String mostrarPaginaLogin() {
        return "login"; // templates/login.html
    }
}
