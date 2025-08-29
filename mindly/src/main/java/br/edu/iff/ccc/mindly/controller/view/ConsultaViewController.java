package br.edu.iff.ccc.mindly.controller.view;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import br.edu.iff.ccc.mindly.dto.ConsultaDTO;
import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.service.ConsultaService;
import br.edu.iff.ccc.mindly.entities.Paciente;
import br.edu.iff.ccc.mindly.service.PacienteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/Consultas")
public class ConsultaViewController {

    private final ConsultaService consultaService;
    private final PacienteService pacienteService;
    //private final DisponibilidadeService disponibilidadeService;

    public ConsultaViewController(ConsultaService consultaService,
                                  PacienteService pacienteService){
    //                              DisponibilidadeService disponibilidadeService) {
        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
    //    this.disponibilidadeService = disponibilidadeService;
    }

    @GetMapping
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", ConsultaService.listarConsultas()); // Lista as consultas
        return "consultas/lista"; // templates/consultas/lista.html
    }
    @GetMapping("/adicionar")
    public String novoConsulta(Model model) {
        model.addAttribute("consulta", new ConsultaDTO());
        model.addAttribute("pacientes", PacienteService.listarPacientes());
    //    model.addAttribute("disponibilidades", disponibilidadeService.listarDisponiveis());
        return "consultas/adicionar"; // templates/consultas/adicionar.html
    }

    @PostMapping("/consultas")
    public String salvarConsulta(@Valid @ModelAttribute("consulta") ConsultaDTO dto,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pacientes", PacienteService.listarPacientes());
    //        model.addAttribute("disponibilidades", disponibilidadeService.listarDisponiveis());
            return "consultas/adicionar"; // Volta para a página de adicionar
        }

        ConsultaService.adicionarConsulta(dto);
        return "redirect:/consultas";
    }

    @GetMapping("/editar/{id}")
    public String editarConsulta(@PathVariable Long id) {
        return "consultas/editar"; // templates/consultas/editar.html
    }

    
    
}
