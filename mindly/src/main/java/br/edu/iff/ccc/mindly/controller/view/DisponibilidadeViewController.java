package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disponibilidades")
public class DisponibilidadeViewController {

    private final DisponibilidadeService service;

    public DisponibilidadeViewController(DisponibilidadeService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("disponibilidades", service.listarTodas());
        return "disponibilidades/lista";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("disponibilidade", new DisponibilidadeDTO());
        return "disponibilidades/nova";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("disponibilidade") DisponibilidadeDTO dto,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "disponibilidades/nova";
        }
        service.salvar(dto);
        return "redirect:/disponibilidades";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("disponibilidade", service.buscarDTO(id));
        return "disponibilidades/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("disponibilidade") DisponibilidadeDTO dto,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "disponibilidades/editar";
        }
        service.atualizar(id, dto);
        return "redirect:/disponibilidades";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/disponibilidades";
    }
}
