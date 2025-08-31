package br.edu.iff.ccc.mindly.controller.view;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.service.ConsultaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api")
public class ConsultaViewController {

    @GetMapping("/consultas")
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", ConsultaService.listarConsultas()); // Lista as consultas
        return "consultas/lista"; // templates/consultas/lista.html
    }
    @GetMapping("/consultas/adicionar")
    public String novoConsulta(Model model) {
        model.addAttribute("consulta", new Consulta());
        return "consultas/adicionar"; // templates/consultas/adicionar.html
    }

    @GetMapping("/consultas/editar/{id}")
    public String editarConsulta(@PathVariable Long id) {
        return "consultas/editar"; // templates/consultas/editar.html
    }

    @PostMapping("/consultas")
    public String salvarConsulta(@RequestBody Consulta consulta) {
        ConsultaService.adicionarConsulta(consulta); // Salva a consulta
        return "redirect:/api/consultas";
    }
    
}
