package br.edu.iff.ccc.mindly.service;

import org.springframework.stereotype.Service;
import br.edu.iff.ccc.mindly.entities.Login;
@Service
public class LoginService {

    public String login(Login request) {
        // Aqui no futuro colocaremos validação no banco
        return "Login recebido: " + request.getEmail();
    }
}
