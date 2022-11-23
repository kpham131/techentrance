package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.model.Skill;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.SkillService;
import com.techentrance.techentrance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final SkillService skillService;

    @GetMapping("/users/{userid}/skills")
    public String userSkills(HttpServletRequest request, HttpServletResponse response, @PathVariable("userid") String userId, Model model) {
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

        List<Skill> skills = skillService.getSkillsByUserId(user.getId());
        model.addAttribute("skills", skills);

        return "addskills";
    }

    @PostMapping("/users/{userid}/skills")
    public String userSkills(@RequestBody List<String> skillList, @PathVariable("userid") String userId) {
        UUID uuidUserId = UUID.fromString(userId);
        User user = userService.getUserById(uuidUserId);

        if(user==null) return "error";

        userService.insertSkills(skillList, user);

        return "redirect:/users/"+ userId + "/profile";
    }


    @GetMapping("/users/{userid}/personalInfo")
    public String newPersonalInfoGet(HttpServletResponse response, @PathVariable("userid") String userId) {
        UUID uuidUserId = UUID.fromString(userId);
        User user = userService.getUserById(uuidUserId);

        if(user==null) return "error";
        Cookie userIdCookie = new Cookie("userId", userId);
        userIdCookie.setMaxAge(3600);
        response.addCookie(userIdCookie);

//        Cookie cookie = security.validateSession(request);
//        if(cookie==null){
//            Cookie redirect = new Cookie("redirectURL", "users/"+userId+"/skills");
//            response.addCookie(redirect);
//            return "redirect:/login";
//        }
//        response.addCookie(cookie);


        return "newPersonalInfo";
    }

    @GetMapping("/users/{userid}/editPersonalInfo")
    public String editPersonalInfoGet(HttpServletResponse response, @PathVariable("userid") String userId, Model model) {
        UUID uuidUserId = UUID.fromString(userId);
        User user = userService.getUserById(uuidUserId);

        if(user==null) return "error";
        Cookie userIdCookie = new Cookie("userId", userId);
        userIdCookie.setMaxAge(3600);
        response.addCookie(userIdCookie);

//        Cookie cookie = security.validateSession(request);
//        if(cookie==null){
//            Cookie redirect = new Cookie("redirectURL", "users/"+userId+"/skills");
//            response.addCookie(redirect);
//            return "redirect:/login";
//        }
//        response.addCookie(cookie);
        model.addAttribute("user", user);

        return "editprofile";
    }

    @PostMapping("/users/{userid}/personalInfo")
    public String newPersonalInfoPost(@PathVariable("userid") String userId, @ModelAttribute User user, Model model) {
        UUID uuidUserId = UUID.fromString(userId);
        User foundUser = userService.getUserById(uuidUserId);


        if(foundUser==null) return "error";

        foundUser = userToFoundUser(user, foundUser);

        userService.saveUser(foundUser);

        return "redirect:/users/"+ userId + "/skills";
    }

    @PostMapping("/users/{userid}/editPersonalInfo")
    public String editPersonalInfoPost(@PathVariable("userid") String userId, @ModelAttribute User user, Model model) {
        UUID uuidUserId = UUID.fromString(userId);
        User foundUser = userService.getUserById(uuidUserId);


        if(foundUser==null) return "error";

        foundUser = userToFoundUser(user, foundUser);

        userService.saveUser(foundUser);

        return "redirect:/users/"+ userId + "/profile";
    }


    @GetMapping("/users/{userid}/profile")
    public String profileView(HttpServletRequest request, HttpServletResponse response, @PathVariable("userid") String userId, Model model) {
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

        List<Skill> skills = skillService.getSkillsByUserId(user.getId());


        model.addAttribute("user", user);
        model.addAttribute("skills", skills);
        return "profile";
    }

    public User userToFoundUser(User user, User foundUser) {
        if(user.getFirstName()!=null && !user.getFirstName().isEmpty()) {
            foundUser.setFirstName(user.getFirstName());
        }

        if(user.getLastName()!=null && !user.getLastName().isEmpty()) {
            foundUser.setLastName(user.getLastName());
        }

        if(user.getPhoneNumber()!=null && !user.getPhoneNumber().isEmpty()) {
            foundUser.setPhoneNumber(user.getPhoneNumber());
        }

        if(user.getGpa()!=null && !user.getGpa().isEmpty()) {
            foundUser.setGpa(user.getGpa());
        } else {
            foundUser.setGpa(null);
        }

        if(user.getCity()!=null && !user.getCity().isEmpty()) {
            foundUser.setCity(user.getCity());
        } else {
            foundUser.setCity(null);
        }

        if(user.getState()!=null && !user.getState().isEmpty()) {
            foundUser.setState(user.getState());
        } else {
            foundUser.setState(null);
        }
        return foundUser;
    }
}
