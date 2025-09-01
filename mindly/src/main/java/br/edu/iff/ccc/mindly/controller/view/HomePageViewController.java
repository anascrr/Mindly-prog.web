package br.edu.iff.ccc.mindly.controller.view;

import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageViewController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping({ "/", "/home" })
    public String homePage(@RequestParam(required = false) String data, Model model) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataSelecionada = (data != null) ? LocalDate.parse(data) : hoje;

        List<Consulta> consultasHoje = consultaService.obterConsultasPorData(hoje);
        List<Consulta> consultasData = consultaService.obterConsultasPorData(dataSelecionada);

        // Garante que nunca será null
        model.addAttribute("consultasHoje", consultasHoje != null ? consultasHoje : new ArrayList<>());
        model.addAttribute("consultasData", consultasData != null ? consultasData : new ArrayList<>());
        model.addAttribute("dataSelecionada", dataSelecionada);
        model.addAttribute("hoje", hoje);

        return "index";
    }
}
