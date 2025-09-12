package br.edu.iff.ccc.mindly.controller.view;

import br.edu.iff.ccc.mindly.entities.Consulta;
import br.edu.iff.ccc.mindly.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Controller
public class HomePageViewController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping({"/home" })
    public String homePage(@RequestParam(required = false) String data, Model model) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataSelecionada = (data != null) ? LocalDate.parse(data) : hoje;

        List<Consulta> consultasHoje = consultaService.obterConsultasPorData(hoje);
        consultasHoje.sort(Comparator.comparing(Consulta::getDataConsulta));

        List<Consulta> consultasData = consultaService.obterConsultasPorData(dataSelecionada);
        consultasData.sort(Comparator.comparing(Consulta::getDataConsulta));

        model.addAttribute("consultasHoje", consultasHoje);
        model.addAttribute("consultasData", consultasData);
        model.addAttribute("dataSelecionada", dataSelecionada);
        model.addAttribute("hoje", hoje);
        model.addAttribute("activePage", "home");

        return "index";
    }
}
