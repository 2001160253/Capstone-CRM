package com.CRM.Capstone.Project.Repository;

import com.CRM.Capstone.Project.Entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository  extends JpaRepository<StatusEntity, Integer> {
}
