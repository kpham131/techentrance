package com.techentrance.techentrance.controller;


import com.techentrance.techentrance.model.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String userRegistration(@ModelAttribute LoginForm loginForm, Model model) {
        System.out.println(loginForm.toString());
        // validate
        System.out.println(loginForm.getUser());
        System.out.println(loginForm.getPassword());
        model.addAttribute("user", loginForm.getUser());
        model.addAttribute("password", loginForm.getPassword());
        return "result";
    }
}
