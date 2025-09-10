package br.edu.iff.ccc.mindly.controller.view;

import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.service.ConsultaService;
import br.edu.iff.ccc.mindly.service.PacienteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ConsultaViewController {

    private final ConsultaService consultaService;
    private final PacienteService pacienteService;

    public ConsultaViewController(ConsultaService consultaService, PacienteService pacienteService) {
        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/consultas")
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.listarConsultas());
        model.addAttribute("activePage", "consultas");
        return "consultas/lista"; // templates/consultas/lista.html
    }

    @GetMapping("/consultas/adicionar")
    public String novoConsulta(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "consultas/adicionar"; // templates/consultas/adicionar.html
    }

    @GetMapping("/consultas/editar/{id}")
    public String editarConsulta(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.buscarPorId(id).orElse(null);
        if (consulta == null) {
            return "redirect:/api/consultas";
        }
        model.addAttribute("consulta", consulta);
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "consultas/editar"; // templates/consultas/editar.html
    }

    @PostMapping("/consultas")
    public String salvarConsulta(
            @RequestParam Long pacienteId,
            @RequestParam String medico,
            @RequestParam String dataConsulta,
            @RequestParam(required = false) String observacao) {

        Paciente paciente = pacienteService.buscarPorId(pacienteId);
        if (paciente == null) {
            return "redirect:/consultas/adicionar";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dataConsulta, formatter);

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataConsulta(dataHora);
        consulta.setObservacao(observacao);

        consultaService.salvar(consulta);
        return "redirect:/consultas";
    }

    @PostMapping("/consultas/editar")
    public String atualizarConsulta(
            @ModelAttribute("consulta") Consulta consulta,
            @RequestParam Long pacienteId) {
        Paciente paciente = pacienteService.buscarPorId(pacienteId);
        consulta.setPaciente(paciente);
        consultaService.atualizar(consulta.getId(), consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/consultas/excluir/{id}")
    public String excluirConsulta(@PathVariable Long id) {
        consultaService.remover(id);
        return "redirect:/consultas";
    }
}
