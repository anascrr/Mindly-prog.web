package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.mindly.dto.ConsultaDTO;

@Controller
@RequestMapping("/agendamentos")
public class ConsultaViewController {

    @GetMapping("/novo")
    public String novoAgendamento(Model model) {
        model.addAttribute("consulta", new ConsultaDTO());
        return "novo-agendamento";
    }

    @GetMapping("/editar/{id}")
    public String editarAgendamento(@PathVariable Long id, Model model) {
        model.addAttribute("consultaId", id);
        return "editar-agendamento";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelarAgendamento(@PathVariable Long id, Model model) {
        model.addAttribute("consultaId", id);
        return "cancelar-agendamento";
    }
}
