package todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import todolist.form.LoginForm;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String getLogin(LoginForm loginForm) {
        return "login/index";
    }
    
}