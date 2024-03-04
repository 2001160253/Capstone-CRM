package com.CRM.Capstone.Project.Repository;

import com.CRM.Capstone.Project.Entity.JobsEntity;
import com.CRM.Capstone.Project.Entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobsEntity, Integer> {
}
