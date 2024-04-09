package com.ooselab.recruitmentsystemapi.userModule;

public class Job {
    public int jobId;
    public String jobName;
    public int jobSalary;
    public int yearOfSkill;
    public String skill;
    public String noticePeriod;
    public int userId;

    public int getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobSalary() {
        return jobSalary;
    }

    public int getYearOfSkill() {
        return yearOfSkill;
    }

    public String getSkill() {
        return skill;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public int getUserId() {
        return userId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobSalary(int jobSalary) {
        this.jobSalary = jobSalary;
    }

    public void setYearOfSkill(int yearOfSkill) {
        this.yearOfSkill = yearOfSkill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
