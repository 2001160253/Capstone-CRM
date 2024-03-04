package com.CRM.Capstone.Project.Repository;

import com.CRM.Capstone.Project.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    List<UsersEntity> findByEmailAndPassword(String email, String password);

    List<UsersEntity> findByRolesEntityId(int id);
}
