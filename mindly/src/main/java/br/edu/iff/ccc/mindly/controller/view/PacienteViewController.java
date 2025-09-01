package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.service.PacienteService;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PacienteViewController {

    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", PacienteService.listarTodos());
        return "pacientes/layout"; // templates/pacientes/layout.html
    }

    @GetMapping("/pacientes/adicionar")
    public String novoPaciente(Model model) {
        model.addAttribute("pacienteDTO", new PacienteDTO());
        return "pacientes/adicionar"; // templates/pacientes/adicionar.html
    }

    @GetMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = PacienteService.buscarPorId(id);
        PacienteDTO pacienteDTO = new PacienteDTO(paciente);
        model.addAttribute("pacienteDTO", pacienteDTO);
        return "pacientes/editar"; // templates/pacientes/editar.html
    }

    @PostMapping("/pacientes")
    public String salvarPaciente(
            @Valid @ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "pacientes/adicionar";
        }
        Paciente paciente = pacienteDTO.toEntity();
        PacienteService.adicionarPaciente(paciente);
        return "redirect:/pacientes";
    }

    @PostMapping("/pacientes/editar")
    public String atualizarPaciente(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO) {
        PacienteService.atualizarPaciente(pacienteDTO);
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        PacienteService.excluirPaciente(id);
        return "redirect:/pacientes";
    }
}