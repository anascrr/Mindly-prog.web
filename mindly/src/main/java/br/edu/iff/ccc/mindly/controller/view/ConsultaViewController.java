package br.edu.iff.ccc.mindly.controller.view;

import br.edu.iff.ccc.mindly.dto.ConsultaRequestDTO; // Usando o DTO para entrada de dados
import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.exception.BusinessException;
import br.edu.iff.ccc.mindly.service.ConsultaService;
import br.edu.iff.ccc.mindly.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        if (!model.containsAttribute("consultaDTO")) {
             model.addAttribute("consultaDTO", new ConsultaRequestDTO(null, null, null, null));
        }
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "consultas/adicionar";
    }

    @PostMapping("/adicionar")
    public String salvarConsulta(ConsultaRequestDTO dto, RedirectAttributes redirectAttributes, Model model) {
        try {
            consultaService.criarConsulta(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Consulta agendada com sucesso!");
            return "redirect:/consultas";
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("consultaDTO", dto);
            model.addAttribute("pacientes", pacienteService.listarTodos());
            return "consultas/adicionar";
        }
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.buscarConsultaPorId(id); // Agora retorna a entidade ou lança exceção
        model.addAttribute("consulta", consulta);
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "consultas/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizarConsulta(@PathVariable Long id, ConsultaRequestDTO dto, RedirectAttributes redirectAttributes, Model model) {
        try {
            consultaService.atualizarConsulta(id, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Consulta atualizada com sucesso!");
            return "redirect:/consultas";
        } catch (BusinessException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("consulta", consultaService.buscarConsultaPorId(id)); // Recarrega a consulta para o form
            model.addAttribute("pacientes", pacienteService.listarTodos());
            return "consultas/editar";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirConsulta(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        consultaService.remover(id); // Este método está correto no novo Service
        redirectAttributes.addFlashAttribute("successMessage", "Consulta excluída com sucesso!");
        return "redirect:/consultas";
    }
}