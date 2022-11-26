package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.MainGET;
import com.techentrance.techentrance.Utils;
import com.techentrance.techentrance.model.Job;
import com.techentrance.techentrance.model.Skill;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.model.UserJob;
import com.techentrance.techentrance.service.JobService;
import com.techentrance.techentrance.service.SkillService;
import com.techentrance.techentrance.service.UserJobService;
import com.techentrance.techentrance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final UserJobService userJobService;
    private final UserService userService;

    @GetMapping("/users/{userId}/jobs/{jobId}")
    public String jobDetailGet(HttpServletRequest request, HttpServletResponse response, @PathVariable("userId") String userId, @PathVariable("jobId") String jobId, Model model) {

        UUID uuidUserId = UUID.fromString(userId);
        User foundUser = userService.getUserById(uuidUserId);


        if(foundUser==null) return "error";

//        Job job = jobs.get(Integer.parseInt(jobId));
        Job job = jobService.getJobById(jobId);

//        job.setContents(Jsoup.parse(job.getContents()).text());
        job.setContents(job.getContents().replaceAll("h1", "p"));
        job.setContents(job.getContents().replaceAll("b>", "h4>"));
        job.setContents(job.getContents().replaceAll("<br>", ""));
        job.setContents(job.getContents().replaceAll("<li>", "<li>● "));
        model.addAttribute("job", job);

        if (!userJobService.isSaved(foundUser, job)) return "jobDetail";
        return "jobDetailSaved";

    }

}
