package com.techentrance.techentrance.controller;

import com.techentrance.techentrance.MainGET;
import com.techentrance.techentrance.model.Job;
import com.techentrance.techentrance.model.Skill;
import com.techentrance.techentrance.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/jobs/{jobId}")
    public String jobDetailGet(@PathVariable("jobId") String jobId, Model model) {

        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Python"));

        List<Job> jobs = MainGET.getJobsWithSkills(skills);

//        Job job = jobs.get(Integer.parseInt(jobId));
        Job job = jobService.getJobById(jobId);

//        job.setContents(Jsoup.parse(job.getContents()).text());
        job.setContents(job.getContents().replaceAll("h1", "p"));
        job.setContents(job.getContents().replaceAll("b>", "h4>"));
        job.setContents(job.getContents().replaceAll("<br>", ""));
        job.setContents(job.getContents().replaceAll("<li>", "<li>● "));
        model.addAttribute("job", job);

        return "jobDetail";
    }

}
