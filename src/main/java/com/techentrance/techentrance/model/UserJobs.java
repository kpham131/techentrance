package com.techentrance.techentrance.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "userjobs")
public class UserJobs {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int jobId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userId;

    public UserJobs() {
    }

    public UserJobs(int jobId, int userId) {
        this.jobId = jobId;
        this.userId = userId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
