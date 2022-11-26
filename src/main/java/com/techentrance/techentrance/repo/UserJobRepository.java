package com.techentrance.techentrance.repo;

import com.techentrance.techentrance.model.UserJob;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserJobRepository extends CrudRepository<UserJob, Integer> {

    @Modifying
    @Query("DELETE FROM user_jobs WHERE user_id=:userId AND job_id=:jobId")
    void deleteUserJobWithUserIdAndJobId(@Param("userId") UUID userId, @Param("jobId") String jobId);


    @Query("SELECT user_id FROM user_jobs WHERE user_id=:userId AND job_id=:jobId")
    UUID findById(@Param("userId") UUID userId, @Param("jobId") String jobId) ;

}
