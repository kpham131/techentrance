package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.MainGET;
import com.techentrance.techentrance.model.Job;
import com.techentrance.techentrance.model.Skill;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.security.Security;
import com.techentrance.techentrance.service.JobService;
import com.techentrance.techentrance.service.SkillService;
import com.techentrance.techentrance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller @RequiredArgsConstructor
public class HomeController {
    private final Security security;
    private final UserService userService;
    private final SkillService skillService;
    private final JobService jobService;

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
        List<Skill> skills = skillService.getSkillsByUserId(UUID.fromString(userId));

        List<Job> jobs = MainGET.getJobsWithSkills(skills);

        jobService.saveJobs(jobs);

        model.addAttribute("jobs", jobs);
        model.addAttribute("userId", userId);

        return "index";
    }


    @GetMapping("/users/{userid}/about")
    public String about(HttpServletRequest request, HttpServletResponse response, @PathVariable("userid") String userId, Model model) {
        Cookie cookie = security.validateSession(request);
        if(cookie==null){
            return "redirect:/login";
        }

        response.addCookie(cookie);

        return "about";
    }
}
