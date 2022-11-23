package com.techentrance.techentrance.model;

import javax.persistence.*;

@Entity(name = "jobs")
public class Job {
    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(100)")
    private String id;

    private String contents;

    private String name;

    private String locations;

    private String ref;

    private String company;


    public Job() {

    }

    public Job(String jobId, String contents, String name, String locations, String ref, String companyName) {
        this.id = jobId;
        this.contents = contents;
        this.name = name;
        this.locations = locations;
        this.ref = ref;
        this.company = companyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLocations() {
        return locations;
    }

    public void setLocation(String locations) {
        this.locations = locations;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
