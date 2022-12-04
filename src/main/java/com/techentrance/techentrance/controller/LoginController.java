package com.techentrance.techentrance.controller;


import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;


@Controller @RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final Security security;

    @GetMapping("/login")
    public String loginView(HttpServletRequest request, HttpServletResponse response) {
        // check if user already logged in
        Cookie cookie = security.validateSession(request);
        if(cookie!=null){
            response.addCookie(cookie);
            UUID sessionId = UUID.fromString(cookie.getValue());
            User foundUser = userService.getUserBySessionId(sessionId);
            return "redirect:/users/"+foundUser.getId();
        }

        String redirect = Utils.getCookie(request, "redirectURL");
        if (redirect!=null) {
            response.addCookie(new Cookie("redirectURL", redirect));
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user, Model model) {

        // validate
        UUID sessionId = security.authenticate(user);
        if(sessionId==null){
//            return "redirect:/login";
            return "loginerror";
        }
        else{
            Cookie cookie = new Cookie("SessionId", sessionId.toString());
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            String redirect = Utils.getCookie(request, "redirectURL");
            if(redirect == null) return "redirect:/";
            return "redirect:/"+redirect;
        }
    }

    @GetMapping("/signup")
    public String signUpView() {
        return "signup";
    }

    @PostMapping("/signup")
    public String userRegistration(HttpServletResponse response, @ModelAttribute User user, Model model) {
        // validate if user exists
        User foundUser = userService.getUserByEmail(user.getEmail());
        if(foundUser!=null) {
            // go back to signup page and say user exists
//            return signUpView();
            return "signuperror";
        }
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().concat(user.getEmail()).getBytes(StandardCharsets.UTF_8)));

        // add to model to send to front end
        model.addAttribute("user", user);

        UUID sessionId = UUID.randomUUID();
        user.setSessionId(sessionId);
        Cookie cookie = new Cookie("SessionId", sessionId.toString());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);


        userService.saveUser(user);
        return "redirect:/users/"+ user.getId() + "/personalInfo";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // check if user already logged in
        response.addCookie(new Cookie("SessionId", "null"));
        return "login";
    }

}
