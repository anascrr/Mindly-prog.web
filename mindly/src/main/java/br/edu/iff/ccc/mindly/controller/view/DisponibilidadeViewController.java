package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disponibilidades")
public class DisponibilidadeViewController {

    @GetMapping
    public String listarDisponibilidades() {
        return "disponibilidades/lista"; // templates/disponibilidades/lista.html
    }

    @GetMapping("/nova")
    public String novaDisponibilidade() {
        return "disponibilidades/nova"; // templates/disponibilidades/nova.html
    }

    @GetMapping("/editar/{id}")
    public String editarDisponibilidade(@PathVariable Long id) {
        return "disponibilidades/editar"; // templates/disponibilidades/editar.html
    }
}
