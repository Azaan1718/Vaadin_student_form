package com.azaan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



import jakarta.persistence.GenerationType;

@Entity
public class Student {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String schoolname;
    private String firstname;
    private String lastname;
    private String gender;
    private String aspiration;
    private String grade;
    private String section;
    private String emailid;
    private boolean leader;
    private String comments;
    private String guardianname;
    private String guardianrelation;
    private String guardianemail;
    public String getGuardianemail() {
        return guardianemail;
    }
    public void setGuardianemail(String guardianemail) {
        this.guardianemail = guardianemail;
    }
    public String getSchoolname() {
        return schoolname;
    }
    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAspiration() {
        return aspiration;
    }
    public void setAspiration(String aspiration) {
        this.aspiration = aspiration;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public String getEmailid() {
        return emailid;
    }
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
    public boolean isLeader() {
        return leader;
    }
    public void setLeader(boolean leader) {
        this.leader = leader;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getGuardianname() {
        return guardianname;
    }
    public void setGuardianname(String guardianname) {
        this.guardianname = guardianname;
    }
    public String getGuardianrelation() {
        return guardianrelation;
    }
    public void setGuardianrelation(String guardianrelation) {
        this.guardianrelation = guardianrelation;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
