package com.CRM.Capstone.Project.Entity;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobsEntity jobsEntity;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity statusEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity users;

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    public UsersEntity getUsers() {
        return users;
    }

    public void setUsers(UsersEntity users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public JobsEntity getJobsEntity() {
        return jobsEntity;
    }

    public void setJobsEntity(JobsEntity jobsEntity) {
        this.jobsEntity = jobsEntity;
    }

    public String formatDate(Date date){
        if(date==null){
            return null;
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        return outputFormat.format(date);
    }

    public String getFormatStartDate(){
        return formatDate(startDate);
    }

    public String getFormatEndDate(){
        return formatDate(endDate);
    }
}
