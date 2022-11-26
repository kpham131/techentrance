package com.techentrance.techentrance.service;

import com.techentrance.techentrance.model.Job;
import com.techentrance.techentrance.model.User;
import com.techentrance.techentrance.model.UserJob;
import com.techentrance.techentrance.repo.UserJobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserJobService {
    private final UserJobRepository userJobRepository;

    public boolean isSaved(User user, Job job) {
        if (userJobRepository.findById(user.getId(), job.getId()) != null) return true;
        return false;
    }
}
