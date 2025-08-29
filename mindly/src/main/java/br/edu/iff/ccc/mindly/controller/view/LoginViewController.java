package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.mindly.entities.Disponibilidade;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/login")
public class LoginViewController {

    @GetMapping
    public String loginPage() {
        return "login"; // -> templates/login.html
    }
}
