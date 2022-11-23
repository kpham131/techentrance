package com.techentrance.techentrance.repo;

import com.techentrance.techentrance.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Integer> {
    Job findById(String id);
}
