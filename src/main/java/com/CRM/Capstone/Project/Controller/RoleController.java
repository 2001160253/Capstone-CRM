package com.CRM.Capstone.Project.Controller;

import com.CRM.Capstone.Project.Entity.RoleEntity;
import com.CRM.Capstone.Project.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/show")
    public String showRole(Model model){
        List<RoleEntity> listRole = roleService.getAllRole();
        model.addAttribute("listRole", listRole);

        return "role-table";
    }

    @GetMapping("/add")
    public String addRole(){
        return "role-add";
    }

    @PostMapping("/add")
    public String progressAddRole(Model model, @RequestParam String name, @RequestParam String pass){
       boolean isSuccess = roleService.addRole(name, pass);
       model.addAttribute("isSuccess", isSuccess);
       return "role-add";
    }

    @GetMapping("/delete/{roleID}")
    public String progressDeleteRole(@PathVariable(name = "roleID") int id){
        roleService.deleteRole(id);

        return "redirect:/role/show";
    }

    @GetMapping("/update/{roleId}")
    public String showUpdateRole(Model model, @PathVariable int roleId) {
        Optional<RoleEntity> optionalRolesEntity = roleService.getOptionalRoleById(roleId);
        optionalRolesEntity.ifPresent(value -> {
            model.addAttribute("updateRoleEntity", value);
        });

        return "role-update";
    }

    @PostMapping("/update/{roleId}")
    public String progressUpdateRole(Model model, @PathVariable int roleId, @RequestParam String roleName, @RequestParam String roleDesc) {
        // Khởi tạo 1 đối tượng RolesEntity và gán giá trị cho 3 thuộc tính của đối tượng đó
        RoleEntity updateRoleEntity = new RoleEntity();
        updateRoleEntity.setId(roleId); // có setId -> update
        updateRoleEntity.setName(roleName); // gán roleName mới mà người dùng nhập vào form update
        updateRoleEntity.setDescription(roleDesc); // gán roleDesc mới mà người dùng nhập vào form update
        boolean isUpdateSuccess = roleService.updateRole(updateRoleEntity);
        model.addAttribute("isUpdateSuccess", isUpdateSuccess); // thêm isUpdateSuccess vào model để hiển thị ra giao diện thành công hay thất bại
        model.addAttribute("updateRoleEntity", updateRoleEntity); // thêm updateRoleEntity vào model để tránh lỗi NullPointerException

        return "role-update";
    }
}
