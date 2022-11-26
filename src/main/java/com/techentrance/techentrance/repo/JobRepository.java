package com.techentrance.techentrance.repo;

import com.techentrance.techentrance.model.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends CrudRepository<Job, Integer> {
    Job findById(String id);

    @Query("FROM jobs J JOIN user_jobs UJ ON J.id = UJ.job_id WHERE UJ.user_id=:userId")
    List<Job> findByUserId(@Param("userId") UUID userId);
}
