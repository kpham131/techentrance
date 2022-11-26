package com.techentrance.techentrance.service;


import com.techentrance.techentrance.model.*;
import com.techentrance.techentrance.repo.SkillRepository;
import com.techentrance.techentrance.repo.UserJobRepository;
import com.techentrance.techentrance.repo.UserRepository;
import com.techentrance.techentrance.repo.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final UserJobRepository userJobRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserBySessionId(UUID sessionId) {
        return userRepository.findBySessionId(sessionId);
    }
    public User getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public void insertSkills(List<String> skillList, User user) {
        userSkillRepository.deleteAllSkillsWithUserId(user.getId());
        for(String skillString : skillList){
            if (skillString==null || skillString.isEmpty()) continue;
            Skill foundSkill = skillRepository.findBySkill(skillString);
            if(foundSkill==null){
                foundSkill = new Skill(skillString);
                skillRepository.save(foundSkill);
            }
            UserSkill userSkill = new UserSkill(user.getId(), foundSkill.getId());
            userSkillRepository.save(userSkill);
        }
    }

    public void saveJob(Job job, User user) {
        UserJob userJob = new UserJob(job.getId(), user.getId());
        userJobRepository.save(userJob);
    }


    public void unsaveJob(Job job, User user) {
        userJobRepository.deleteUserJobWithUserIdAndJobId(user.getId(), job.getId());
    }
}
