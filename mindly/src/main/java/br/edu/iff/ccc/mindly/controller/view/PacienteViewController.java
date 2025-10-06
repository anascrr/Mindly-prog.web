package br.edu.iff.ccc.mindly.controller.view;

import br.edu.iff.ccc.mindly.dto.PacienteRequestDTO;
import br.edu.iff.ccc.mindly.dto.PacienteUpdateDTO;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.exception.BusinessException;
import br.edu.iff.ccc.mindly.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
public class PacienteViewController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listarTodos());
        model.addAttribute("activePage", "pacientes");
        return "pacientes/lista";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("pacienteRequestDTO", new PacienteRequestDTO());
        return "pacientes/adicionar";
    }

    @PostMapping("/adicionar")
    public String salvarPaciente(@Valid @ModelAttribute("pacienteRequestDTO") PacienteRequestDTO dto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("pacienteRequestDTO", dto);
            return "pacientes/adicionar";
        }
        try {
            pacienteService.criarPaciente(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Paciente cadastrado!");
            return "redirect:/pacientes";
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("pacienteRequestDTO", dto);
            return "pacientes/adicionar";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        model.addAttribute("paciente", paciente);
        return "pacientes/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizarPaciente(@PathVariable Long id, @Valid @ModelAttribute("paciente") Paciente paciente, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("paciente", paciente);
            return "pacientes/editar";
        }
        try {
            PacienteUpdateDTO dto = new PacienteUpdateDTO();
            dto.setNome(paciente.getNome());
            dto.setEmail(paciente.getEmail());
            dto.setTelefone(paciente.getTelefone());
            dto.setEndereco(paciente.getEndereco());
            dto.setDataNascimento(paciente.getDataNascimento());
            dto.setCpf(paciente.getCpf());
            dto.setPlanoSaude(paciente.getPlanoSaude());
            
            pacienteService.atualizarPaciente(id, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Paciente atualizado!");
            return "redirect:/pacientes";
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("paciente", paciente);
            return "pacientes/editar";
        }
    }


    @GetMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pacienteService.excluirPaciente(id);
        redirectAttributes.addFlashAttribute("successMessage", "Paciente excluído!");
        return "redirect:/pacientes";
    }
}