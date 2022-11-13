package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller @RequiredArgsConstructor
public class HomeController {
    private final Security security;
    private final UserService userService;

    @GetMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = security.validateSession(request);
        if(cookie==null){
            return "redirect:/login";
        }
        UUID sessionId = UUID.fromString(cookie.getValue());
        User foundUser = userService.getUserBySessionId(sessionId);
        return "redirect:/users/"+foundUser.getId();
    }

    @GetMapping("/users/{userid}")
    public String homeUser(HttpServletRequest request, HttpServletResponse response, @PathVariable("userid") String userId, Model model) {
        Cookie cookie = security.validateSession(request);
        if(cookie==null){
            return "redirect:/login";
        }

        response.addCookie(cookie);

        return "index";
    }
}
