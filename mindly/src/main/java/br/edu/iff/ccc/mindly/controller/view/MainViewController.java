// package br.edu.iff.ccc.mindly.controller.view;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.ui.Model;

// @Controller
// @RequestMapping(path = "principal")
// public class MainViewController {

//     @GetMapping("/{id}")
//     public String getHomePage(@PathVariable("id") String id, Model model) {
//         model.addAttribute("id", id);
//         model.addAttribute("username", "usuário");
//         model.addAttribute("password", "senha");
//         return "index.html";
//     }
// }
