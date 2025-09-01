package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.mindly.dto.ConsultaDTO;
import br.edu.iff.ccc.mindly.service.ConsultaService;

@Controller
@RequestMapping("/agendamentos")
public class ConsultaViewController {

    private final ConsultaService consultaService;

    public ConsultaViewController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    // LISTAR TODAS AS CONSULTAS
    @GetMapping
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", ConsultaService.listarTodas());
        return "consultas/lista"; // template: consultas/listar.html
    }

    // ADICIONAR NOVA CONSULTA (GET)
    @GetMapping("/novo")
    public String novaConsulta(Model model) {
        model.addAttribute("consulta", new ConsultaDTO());
        return "consultas/adicionar"; // template: consultas/novo.html
    }

    // ADICIONAR NOVA CONSULTA (POST)
    @PostMapping("/novo")
    public String salvarConsulta(@ModelAttribute ConsultaDTO consulta) {
        ConsultaService.salvar(consulta);
        return "redirect:/agendamentos";
    }

    // EDITAR CONSULTA (GET)
    @GetMapping("/editar/{id}")
    public String editarConsulta(@PathVariable Long id, Model model) {
        ConsultaDTO consulta = consultaService.buscarPorId(id);
        model.addAttribute("consulta", consulta);
        return "consultas/editar"; // template: consultas/editar.html
    }

    // EDITAR CONSULTA (POST)
    @PostMapping("/editar/{id}")
    public String atualizarConsulta(@PathVariable Long id, @ModelAttribute ConsultaDTO consulta) {
        consulta.setId(id); // garante que o ID seja mantido
        ConsultaService.salvar(consulta);
        return "redirect:/agendamentos";
    }

    // CANCELAR CONSULTA (POST)
    @PostMapping("/cancelar/{id}")
    public String cancelarConsulta(@PathVariable Long id) {
        consultaService.deletar(id);
        return "redirect:/agendamentos";
    }
}
