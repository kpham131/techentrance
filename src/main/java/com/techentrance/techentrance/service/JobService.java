package com.techentrance.techentrance.service;

import com.techentrance.techentrance.model.Job;
import com.techentrance.techentrance.repo.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JobService {
    private final JobRepository jobRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }
    public Iterable<Job> saveJobs(List<Job> jobs) {
        return jobRepository.saveAll(jobs);
    }

    public Job getJobById(String id) {
        return jobRepository.findById(id);
    }

    public List<Job> getJobsByUserId(UUID userId) {
        return jobRepository.findByUserId(userId);
    }
}
