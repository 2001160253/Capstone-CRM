package com.CRM.Capstone.Project.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity rolesEntity;

    public List<TaskEntity> getTaskEntityList() {
        return taskEntityList;
    }

    public void setTaskEntityList(List<TaskEntity> taskEntityList) {
        this.taskEntityList = taskEntityList;
    }

    @OneToMany(mappedBy = "users")
    private List<TaskEntity> taskEntityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RoleEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(RoleEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }

    public String getFirstName() {
        String[] words = this.fullName.trim().split("\\s+");
        return words[words.length - 1];
    }

    public String getLastName() {
        StringBuilder lastName = new StringBuilder();
        String[] words = this.fullName.trim().split("\\s+");
        for (int i = 0; i < words.length - 1; i++) {
            lastName.append(words[i]).append(" ");
        }
        return lastName.toString().trim();
    }
}
