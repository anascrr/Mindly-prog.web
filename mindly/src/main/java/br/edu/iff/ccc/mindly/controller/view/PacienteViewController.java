package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PacienteViewController {

    @GetMapping("/pacientes")
    public String listarPacientes() {
        return "pacientes/lista"; // templates/pacientes/lista.html
    }

    @GetMapping("/pacientes/novo")
    public String novoPaciente() {
        return "pacientes/novo"; // templates/pacientes/novo.html
    }

    @GetMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id) {
        return "pacientes/editar"; // templates/pacientes/editar.html
    }
}
