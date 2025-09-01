package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.service.ConsultaService;
import br.edu.iff.ccc.mindly.service.PacienteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api")
public class ConsultaViewController {

    private final ConsultaService consultaService;

    public ConsultaViewController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/consultas")
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.listarConsultas()); // Lista as consultas
        return "consultas/lista"; // templates/consultas/lista.html
    }

    @GetMapping("/consultas/adicionar")
    public String novoConsulta(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", PacienteService.listarTodos()); // Adicione esta linha
        return "consultas/adicionar"; // templates/consultas/adicionar.html
    }

    @GetMapping("/consultas/editar/{id}")
    public String editarConsulta(@PathVariable Long id) {
        return "consultas/editar"; // templates/consultas/editar.html
    }

    @PostMapping("/consultas")
    public String salvarConsulta(
            @RequestParam Long pacienteId,
            @RequestParam String medico,
            @RequestParam String dataConsulta,
            @RequestParam(required = false) String observacao) {

        Paciente paciente = PacienteService.buscarPorId(pacienteId);
        if (paciente == null) {
            return "redirect:/api/consultas/adicionar";
        }

        // Aceita formato do input type="datetime-local" (yyyy-MM-ddTHH:mm)
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dataConsulta, formatter);

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataConsulta(dataHora);
        consulta.setObservacao(observacao);

        consultaService.salvar(consulta);
        return "redirect:/api/consultas";
    }

}
