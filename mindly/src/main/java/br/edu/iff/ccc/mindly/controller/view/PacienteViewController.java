package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;

@Controller
@RequestMapping("/pacientes")
public class PacienteViewController {

    @GetMapping
    public String listarPacientes(Model model) {
        // no futuro: model.addAttribute("pacientes", pacienteService.listarTodos());
        return "pacientes";
    }

    @GetMapping("/novo")
    public String novoPaciente(Model model) {
        model.addAttribute("paciente", new PacienteDTO());
        return "novo-paciente";
    }
}
