package com.CRM.Capstone.Project.Service;

import com.CRM.Capstone.Project.Entity.StatusEntity;
import com.CRM.Capstone.Project.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public List<StatusEntity> getAllStatus(){
        return statusRepository.findAll();
    }

    public StatusEntity getStatusByID(int id){
        return statusRepository.findById(id).orElse(null);
    }
}
