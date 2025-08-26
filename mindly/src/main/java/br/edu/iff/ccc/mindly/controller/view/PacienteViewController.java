package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.service.PacienteService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/api")
public class PacienteViewController {

    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", PacienteService.listarPacientes()); // Lista os pacientes
        return "pacientes/layout"; // templates/pacientes/lista.html
    }

    @GetMapping("/pacientes/adicionar")
    public String novoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/adicionar"; // templates/pacientes/novo.html
    }

    @GetMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id) {
        return "pacientes/editar"; // templates/pacientes/editar.html
    }

    @PostMapping("/pacientes")
    public String salvarPaciente(Paciente paciente) {
        PacienteService.adicionarPaciente(paciente); // Salva o paciente
        return "redirect:/api/pacientes";
    }

}