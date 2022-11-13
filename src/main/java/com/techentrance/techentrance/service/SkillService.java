package com.techentrance.techentrance.service;

import com.techentrance.techentrance.model.Skill;
import com.techentrance.techentrance.repo.SkillRepository;
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
public class SkillService {
    private final SkillRepository skillRepository;
    public List<Skill> getSkillsByUserId(UUID userId){
        return skillRepository.findByUserId(userId);
    }
}
