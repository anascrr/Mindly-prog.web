package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AgendamentoViewController {

    @GetMapping("/agendamentos")
    public String listarAgendamentos() {
        return "agendamentos/lista"; // templates/agendamentos/lista.html
    }

    @GetMapping("/agendamentos/novo")
    public String novoAgendamento() {
        return "agendamentos/novo"; // templates/agendamentos/novo.html
    }

    @GetMapping("/agendamentos/editar/{id}")
    public String editarAgendamento(@PathVariable Long id) {
        return "agendamentos/editar"; // templates/agendamentos/editar.html
    }
}
