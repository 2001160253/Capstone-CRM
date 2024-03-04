package com.CRM.Capstone.Project.Service;

import com.CRM.Capstone.Project.Entity.RoleEntity;
import com.CRM.Capstone.Project.Entity.UsersEntity;
import com.CRM.Capstone.Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UsersEntity> getAllUser(){
        return userRepository.findAll();
    }

    public List<UsersEntity> getUser(int id){
        return userRepository.findByRolesEntityId(id);
    }

    public List<UsersEntity> getUserByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }

    public boolean addUser(String email, String password, String fullname, String avatar, RoleEntity roleEntity){
        boolean isSuccess = false;
        UsersEntity user = new UsersEntity();
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullname);
        user.setAvatar(avatar);
        user.setRolesEntity(roleEntity);

        try {
            userRepository.save(user);
            isSuccess=true;
        }catch (Exception e){
            System.out.println("Loi add user" + e.getMessage());
        }

        return isSuccess;
    }

    public void deleteUser(int id){
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }

    public UsersEntity getUserByID(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<UsersEntity> getAllUserByRoleID(int id){
        return userRepository.findByRolesEntityId(id);
    }

    public boolean updateUser(UsersEntity users){
        boolean isSuccess = false;
        try {
            userRepository.save(users);
            isSuccess = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }

    public Optional<UsersEntity> geUsersEntityOptional(int id) {
        return userRepository.findById(id);
    }
}


