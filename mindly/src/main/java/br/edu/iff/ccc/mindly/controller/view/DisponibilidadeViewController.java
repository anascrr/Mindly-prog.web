package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DisponibilidadeViewController {

    @GetMapping("/disponibilidades")
    public String listarDisponibilidades() {
        return "disponibilidades/lista"; // templates/disponibilidades/lista.html
    }

    @GetMapping("/disponibilidades/nova")
    public String novaDisponibilidade() {
        return "disponibilidades/nova"; // templates/disponibilidades/nova.html
    }

    @GetMapping("/disponibilidades/editar/{id}")
    public String editarDisponibilidade(@PathVariable Long id) {
        return "disponibilidades/editar"; // templates/disponibilidades/editar.html
    }
}
