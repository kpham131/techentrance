package com.techentrance.techentrance.repo;

import com.techentrance.techentrance.model.UserSkill;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserSkillRepository extends CrudRepository<UserSkill, Integer> {

    @Modifying
    @Query("DELETE FROM user_skill WHERE user_id=:userId")
    void deleteAllSkillsWithUserId(@Param("userId") UUID userId);
}
