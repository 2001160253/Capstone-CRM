package com.CRM.Capstone.Project.Service;

import com.CRM.Capstone.Project.Entity.*;
import com.CRM.Capstone.Project.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskEntity> getAllTask(){
        return taskRepository.findAll();
    }

    public boolean addTask(String taskName, Date startDate, Date endDate, UsersEntity users, JobsEntity jobsEntity, StatusEntity status){
        boolean isSuccess = false;
        TaskEntity task = new TaskEntity();
        task.setName(taskName);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setJobsEntity(jobsEntity);
        task.setUsers(users);
        task.setStatusEntity(status);

        try {
            taskRepository.save(task);
            isSuccess=true;
        }catch (Exception e){
            System.out.println("Loi add task" + e.getMessage());
        }

        return isSuccess;
    }

    public boolean deleteTask(int id){
        boolean isScucess = false;

        try {
            taskRepository.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return isScucess;
    }

    public List<TaskEntity> getTaskByUserID(int id){
        return taskRepository.findAllByUsersId(id);
    }

    public Optional<TaskEntity> getTaskByTaskID(int id){
        return taskRepository.findById(id);
    }

    public boolean updateProfile(TaskEntity task){
        boolean isSuccess = false;
        try {
            taskRepository.save(task);
            isSuccess = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }

    public List<TaskEntity> getTaskByJobID(int id){
        return taskRepository.findAllByJobsEntityId(id);
    }
}
