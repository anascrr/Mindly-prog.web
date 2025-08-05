package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "principal")
public class PacienteController {

    @GetMapping("/{id}")
    public String getHomePage(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("nome", "João Pereira");
        model.addAttribute("cpf", "000.000.000-00");
        model.addAttribute("dtnasc", "31/02/1999");
        model.addAttribute("sexo", "M");
        model.addAttribute("email", "joazinhopereira@gmail.com");
        model.addAttribute("telefone", "(22) 99999-9999");
        model.addAttribute("endereco", "Avenida Brasil, 1234");
        model.addAttribute("cidade", "Campos");
        model.addAttribute("estado", "RJ");
        model.addAttribute("cep", "28000000");
        return "index.html";
    }
}
