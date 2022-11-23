package com.techentrance.techentrance.model;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "user_jobs")
@IdClass(UserJobPK.class)
public class UserJob implements Serializable {
    @Id
    @Column(name = "job_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(100)")
    private String job_id;

    @Id
    @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(100)")
    @Type(type = "uuid-char")
    private UUID user_id;


    public UserJob() {
    }

    public UserJob(String job_id, UUID user_Id) {
        this.job_id = job_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public UUID getUserId() {
        return user_id;
    }

    public void setUserId(UUID user_id) {
        this.user_id = user_id;
    }

}
