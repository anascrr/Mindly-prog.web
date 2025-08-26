package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ConsultaViewController {

    @GetMapping("/consultas")
    public String listarConsultas() {
        return "consultas"; // templates/consultas/lista.html
    }

    @GetMapping("/consultas/novo")
    public String novoConsulta() {
        return "consultas/novo"; // templates/consultas/novo.html
    }

    @GetMapping("/consultas/editar/{id}")
    public String editarConsulta(@PathVariable Long id) {
        return "consultas/editar"; // templates/consultas/editar.html
    }
}
