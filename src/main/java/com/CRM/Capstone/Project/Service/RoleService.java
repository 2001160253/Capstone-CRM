package com.CRM.Capstone.Project.Service;

import com.CRM.Capstone.Project.Entity.RoleEntity;
import com.CRM.Capstone.Project.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleEntity> getAllRole(){
        return roleRepository.findAll();
    }

    public RoleEntity getRoleByName(String name){
        return roleRepository.findByName(name);
    }

    public boolean addRole(String name, String desc){
        boolean isSuccess = false;
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(name);
        roleEntity.setDescription(desc);

        try {
            roleRepository.save(roleEntity);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }

        return isSuccess;
    }

    public  void deleteRole(int roleID){
        try {
            roleRepository.deleteById(roleID);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public Optional<RoleEntity> getOptionalRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }
    public RoleEntity getRolesEntityById(int roleId) {
        Optional<RoleEntity> optionalRole = getOptionalRoleById(roleId);
        RoleEntity rolesEntity = null;
        if (optionalRole.isPresent()) {
            rolesEntity = optionalRole.get();
        }

        return rolesEntity;
    }

    public boolean updateRole(RoleEntity roleEntity){
        boolean isSuccess = false;
        try {
            roleRepository.save(roleEntity);
            isSuccess = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
}
