package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;
import br.edu.iff.ccc.mindly.service.PacienteService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/pacientes")
public class PacienteViewController {

    private final PacienteService pacienteService;

    public PacienteViewController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "paciente/lista"; // paciente/lista.html
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoPaciente(Model model) {
        model.addAttribute("paciente", new PacienteDTO());
        return "paciente/formulario"; // paciente/formulario.html
    }

    @PostMapping
    public String salvarPaciente(@ModelAttribute PacienteDTO pacienteDTO) {
        pacienteService.salvarPaciente(pacienteDTO);
        return "redirect:/pacientes";
    }
}