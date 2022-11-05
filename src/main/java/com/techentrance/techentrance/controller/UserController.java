package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final Security security;
    private final UserService userService;

    @GetMapping("/users/{userid}/skills")
    public String userSkills(HttpServletRequest request, HttpServletResponse response, @PathVariable("userid") String userId) {
        UUID uuidUserId = UUID.fromString(userId);
        User user = userService.getUserById(uuidUserId);

        if(user==null) return "error";

        Cookie cookie = security.validateSession(request);
        if(cookie==null){
            Cookie redirect = new Cookie("redirectURL", "users/"+userId+"/skills");
            response.addCookie(redirect);
            return "redirect:/login";
        }
        response.addCookie(cookie);
        // TODO: send the current skills to front end
        return "addskills";
    }

    @PostMapping("/users/{userid}/skills")
    public String userSkills(@RequestBody List<String> skillList, @PathVariable("userid") String userId) {
        UUID uuidUserId = UUID.fromString(userId);
        User user = userService.getUserById(uuidUserId);

        if(user==null) return "error";

        System.out.println(skillList);

        // TODO: insert to database

        // TODO: redirect to user profile
        return "redirect:/";
    }
}
