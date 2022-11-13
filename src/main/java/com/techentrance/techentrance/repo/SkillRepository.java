package com.techentrance.techentrance.repo;

import com.techentrance.techentrance.model.Skill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
    Skill findBySkill(String skill);

    @Query("FROM skills S JOIN user_skill SK ON S.id = SK.skill_id WHERE SK.user_id=:userId")
    List<Skill> findByUserId(@Param("userId") UUID userId);
}
