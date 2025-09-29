package br.edu.iff.ccc.mindly.controller.view;

import br.edu.iff.ccc.mindly.dto.ConsultaRequestDTO;
import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.exception.BusinessException;
import br.edu.iff.ccc.mindly.service.ConsultaService;
import br.edu.iff.ccc.mindly.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/consultas")
public class ConsultaViewController {

    private final ConsultaService consultaService;
    private final PacienteService pacienteService;

    public ConsultaViewController(ConsultaService consultaService, PacienteService pacienteService) {
        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.listarTodasConsultasEntidade());
        model.addAttribute("activePage", "consultas");
        return "consultas/lista";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        if (!model.containsAttribute("consultaRequestDTO")) {
            model.addAttribute("consultaRequestDTO", new ConsultaRequestDTO(null, null, null, null));
        }
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "consultas/adicionar";
    }

    @PostMapping("/adicionar")
    public String salvarConsulta(
            // PADRONIZADO: O nome do objeto no @ModelAttribute agora é
            // "consultaRequestDTO".
            @Valid @ModelAttribute("consultaRequestDTO") ConsultaRequestDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pacientes", pacienteService.listarTodos());
            return "consultas/adicionar";
        }

        try {
            consultaService.criarConsulta(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Consulta agendada!");
            return "redirect:/consultas";
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
            // PADRONIZADO: Devolve o DTO com o nome correto para repopular o formulário.
            model.addAttribute("consultaRequestDTO", dto);
            model.addAttribute("pacientes", pacienteService.listarTodos());
            return "consultas/adicionar";
        }
    }

    // ... (os outros métodos como editar e excluir continuam os mesmos) ...
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "consultas/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizarConsulta(@PathVariable Long id, @Valid @ModelAttribute("consulta") Consulta consulta,
            BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pacientes", pacienteService.listarTodos());
            return "consultas/editar";
        }
        try {
            // No caso de edição, estamos trabalhando com a entidade, então o DTO é criado
            // aqui dentro
            ConsultaRequestDTO dto = new ConsultaRequestDTO(
                    consulta.getPaciente().getId(),
                    consulta.getMedico(),
                    consulta.getDataConsulta(),
                    consulta.getObservacao());
            consultaService.atualizarConsulta(id, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Consulta atualizada!");
            return "redirect:/consultas";
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("pacientes", pacienteService.listarTodos());
            return "consultas/editar";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirConsulta(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        consultaService.remover(id);
        redirectAttributes.addFlashAttribute("successMessage", "Consulta excluída!");
        return "redirect:/consultas";
    }
}