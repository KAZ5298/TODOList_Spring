package todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import todolist.form.LoginForm;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String getLogin(Model model, LoginForm loginForm) {
        return "login/index";
    }
    
    @PostMapping("/login")
    public String postLogin() {
        return "redirect:/todo";
    }
}