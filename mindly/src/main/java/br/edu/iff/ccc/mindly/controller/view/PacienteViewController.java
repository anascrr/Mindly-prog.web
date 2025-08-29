package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.mindly.dto.PacienteDTO;
import br.edu.iff.ccc.mindly.service.PacienteService;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/pacientes")
public class PacienteViewController {

    private final PacienteService service;

    public PacienteViewController(PacienteService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", PacienteService.listarPacientes());
        return "pacientes/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("paciente", new PacienteDTO());
        return "pacientes/novo";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("paciente") PacienteDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return "pacientes/novo";
        }
        PacienteService.salvarPaciente(dto);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", service.buscarDTO(id));
        return "pacientes/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("paciente") PacienteDTO dto,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "pacientes/editar";
        }
        service.atualizar(id, dto);
        return "redirect:/pacientes";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/pacientes";
    }
}