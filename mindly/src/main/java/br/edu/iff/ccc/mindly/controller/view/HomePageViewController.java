package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import br.edu.iff.ccc.mindly.service.ConsultaService;

@Controller
public class HomePageViewController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("consultas", consultaService.listarTodas());
        // Se quiser, converta para DTO antes de adicionar ao model
        return "inicio";
    }
}