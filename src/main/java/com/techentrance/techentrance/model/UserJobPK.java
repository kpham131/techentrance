package com.techentrance.techentrance.model;

import java.io.Serializable;
import java.util.UUID;

public class UserJobPK implements Serializable {
    protected String job_id;
    protected UUID user_id;

    public UserJobPK(){}

    public UserJobPK(UUID user_id, String job_id){
        this.user_id = user_id;
        this.job_id = job_id;
    }
}
