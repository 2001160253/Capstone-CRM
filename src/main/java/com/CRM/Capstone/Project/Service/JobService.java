package com.CRM.Capstone.Project.Service;

import com.CRM.Capstone.Project.Entity.JobsEntity;
import com.CRM.Capstone.Project.Entity.RoleEntity;
import com.CRM.Capstone.Project.Entity.StatusEntity;
import com.CRM.Capstone.Project.Entity.UsersEntity;
import com.CRM.Capstone.Project.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.Date;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<JobsEntity> getAllJobs(){
        return jobRepository.findAll();
    }

    public void deleteJob(int id){
        try {
            jobRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("Error delete job: "+e.getMessage());
        }
    }

    public boolean addJob(String name, Date startDate, Date endDate){
        boolean isSuccess = false;
        JobsEntity jobsEntity = new JobsEntity();
        jobsEntity.setName(name);
        jobsEntity.setStartDate(startDate);
        jobsEntity.setEndDate(endDate);

        try {
            jobRepository.save(jobsEntity);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }

        return isSuccess;
    }

    public JobsEntity getJobByID(int id){
        return jobRepository.findById(id).orElse(null);
    }

    public boolean updateJob(JobsEntity job){
        boolean isSuccess = false;
        try {
            jobRepository.save(job);
            isSuccess = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
}
