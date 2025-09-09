package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.service.PacienteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PacienteViewController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "pacientes/lista"; 
    }

    @GetMapping("/pacientes/adicionar")
    public String novoPaciente(Model model) {
        model.addAttribute("pacienteDTO", new PacienteDTO());
        return "pacientes/adicionar"; 
    }

    @GetMapping("/pacientes/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.buscarPorId(id);
        PacienteDTO pacienteDTO = new PacienteDTO(paciente);
        model.addAttribute("pacienteDTO", pacienteDTO);
        return "pacientes/editar"; 
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
        pacienteService.adicionarPaciente(paciente);
        return "redirect:/pacientes";
    }

    @PostMapping("/pacientes/editar")
    public String atualizarPaciente(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO) {
        pacienteService.atualizarPaciente(pacienteDTO);
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
        return "redirect:/pacientes";
    }
}
