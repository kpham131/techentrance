package com.techentrance.techentrance.model;

import java.io.Serializable;
import java.util.UUID;

public class UserSkillPK implements Serializable {
    protected UUID user_id;
    protected UUID skill_id;

    public UserSkillPK(){}
    public UserSkillPK(UUID user_id, UUID skill_id){
        this.user_id = user_id;
        this.skill_id = skill_id;
    }
}
