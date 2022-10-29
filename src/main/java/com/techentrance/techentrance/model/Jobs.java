package com.techentrance.techentrance.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "jobs")
public class Jobs {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int jobId;

    private String contents;

    private String name;

    private String location;

    private String jobType; //intern, FT

    private String companyName;

    public Jobs() {

    }

    public Jobs(int jobId, String contents, String name, String location, String jobType, String companyName) {
        this.jobId = jobId;
        this.contents = contents;
        this.name = name;
        this.location = location;
        this.jobType = jobType;
        this.companyName = companyName;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
