package com.techentrance.techentrance.login;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "login")
public class LoginController {

    @GetMapping
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginForm", new LoginForm());
        modelAndView.setViewName("login.html");
        return modelAndView;
//        model.addAttribute("loginForm", new LoginForm());
//        return "login";
    }

    @PostMapping
    public ModelAndView submit(@ModelAttribute LoginForm loginForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(" ", loginForm);
        System.out.println(loginForm.getUser());
        System.out.println(loginForm.getPassword());
        modelAndView.setViewName("result.html");
        return modelAndView;
//        System.out.println(body);
//        return "body";
    }
}
