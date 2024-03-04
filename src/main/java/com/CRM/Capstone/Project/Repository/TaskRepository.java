package com.CRM.Capstone.Project.Repository;

import com.CRM.Capstone.Project.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findAllByUsersId(int id);

    List<TaskEntity> findAllByJobsEntityId(int id);
}

