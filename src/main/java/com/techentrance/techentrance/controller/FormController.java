package com.techentrance.techentrance.controller;


import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.repo.UserRepository;
import com.techentrance.techentrance.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller @RequiredArgsConstructor
public class FormController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        // get the user

        User foundUser = loginService.getUser(user.getEmail());
        if(foundUser==null) {
            return loginView();
        }
        // validate
        if(foundUser.getPassword().equals(user.getPassword())) {
            return "redirect:/";
        }

        return loginView();
    }

    @GetMapping("/signup")
    public String signUpView() {
        return "signup";
    }

    @PostMapping("/signup")
    public String userRegistration(@ModelAttribute User user, Model model) {
        // validate if user exists
        User foundUser = loginService.getUser(user.getEmail());
        if(foundUser!=null) {
            // go back to signup page and say user exists
            return signUpView();
        }

        // add to model to send to front end
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        loginService.saveUser(user);
        return "result";
    }
}
