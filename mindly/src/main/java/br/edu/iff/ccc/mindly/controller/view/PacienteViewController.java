package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "pacientes/layout"; // paciente/lista.html
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoPaciente(Model model) {
        model.addAttribute("pacientes", new PacienteDTO());
        return "pacientes/adicionar"; // paciente/formulario.html
    }

    @PostMapping
    public String salvarPaciente(@ModelAttribute PacienteDTO pacienteDTO) {
        pacienteService.salvar(pacienteDTO);
        return "redirect:/pacientes";
    }

    // FORMULÁRIO EDITAR PACIENTE
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPaciente(@PathVariable Long id, Model model) {
        PacienteDTO paciente = pacienteService.buscarPorId(id);
        model.addAttribute("paciente", paciente);
        return "pacientes/editar"; // template: pacientes/editar.html
    }

    // SALVAR ALTERAÇÕES PACIENTE
    @PostMapping("/editar/{id}")
    public String atualizarPaciente(@PathVariable Long id, @ModelAttribute PacienteDTO pacienteDTO) {
        pacienteDTO.setId(id); // garante que o ID seja mantido
        pacienteService.salvar(pacienteDTO);
        return "redirect:/pacientes";
    }
}