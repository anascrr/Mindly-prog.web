package br.edu.iff.ccc.mindly.controller.restapi;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.mindly.dto.UsuarioDTO;
import br.edu.iff.ccc.mindly.service.UsuarioService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public String autenticar(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        UsuarioDTO autenticado = usuarioService.autenticar(usuarioDTO.getUsername(), usuarioDTO.getSenha());

        if (autenticado != null) {
            return "redirect:/home"; // redireciona para a Home se login ok
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login"; // volta para tela de login
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
