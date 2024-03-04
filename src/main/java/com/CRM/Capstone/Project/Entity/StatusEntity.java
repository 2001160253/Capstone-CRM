package com.CRM.Capstone.Project.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskEntity> getTaskEntityList() {
        return taskEntityList;
    }

    public void setTaskEntityList(List<TaskEntity> taskEntityList) {
        this.taskEntityList = taskEntityList;
    }

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "statusEntity")
    private List<TaskEntity> taskEntityList;
}
