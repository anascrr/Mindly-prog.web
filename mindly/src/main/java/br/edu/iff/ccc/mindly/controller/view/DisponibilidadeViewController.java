package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disponibilidade")
public class DisponibilidadeViewController {

    @GetMapping
    public String definirDisponibilidade() {
        return "disponibilidade"; // arquivo disponibilidade.html
    }
}
