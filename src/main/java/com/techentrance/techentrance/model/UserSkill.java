package com.techentrance.techentrance.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "user_skill")
@IdClass(UserSkillPK.class)
public class UserSkill implements Serializable {

    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(100)")
    @Type(type = "uuid-char")
    private UUID user_id;

    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "skill_id", updatable = true, nullable = false, columnDefinition = "VARCHAR(100)")
    @Type(type = "uuid-char")
    private UUID skill_id;

    public UserSkill(){}

    public UserSkill(UUID user_id, UUID skill_id){
        this.user_id = user_id;
        this.skill_id = skill_id;
    }

    public UUID getUserId() {
        return user_id;
    }

    public UUID getSkillId() {
        return skill_id;
    }
}
