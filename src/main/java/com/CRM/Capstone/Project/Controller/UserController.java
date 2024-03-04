package com.CRM.Capstone.Project.Controller;

import com.CRM.Capstone.Project.Entity.RoleEntity;
import com.CRM.Capstone.Project.Entity.TaskEntity;
import com.CRM.Capstone.Project.Entity.UsersEntity;
import com.CRM.Capstone.Project.Service.RoleService;
import com.CRM.Capstone.Project.Service.TaskService;
import com.CRM.Capstone.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/show")
    public String showUser(Model model){
        List<UsersEntity> listUser = userService.getAllUser();
        model.addAttribute("listUser", listUser);
        return "user-table";
    }

    @GetMapping("/add")
    public String addUser(Model model){
        List<RoleEntity> listRole = roleService.getAllRole();
        model.addAttribute("listRole", listRole);
        return "user-add";
    }

    @PostMapping("/add")
    public String progressAddUser(@RequestParam String email,@RequestParam String pass,
                                  @RequestParam String fullname,@RequestParam String avatar,
                                  @RequestParam int idRole, Model model){

        List<RoleEntity> listRole = roleService.getAllRole();
        model.addAttribute("listRole", listRole);

        RoleEntity roleEntity = roleService.getRolesEntityById(idRole);

        boolean isAddUserSuccess = userService.addUser(email, pass, fullname, avatar, roleEntity);

        model.addAttribute("isAddUserSuccess", isAddUserSuccess);

        return "user-add";
    }

    @GetMapping("/delete/{userID}")
    public String deleteUser(@PathVariable(name = "userID") int id){
        userService.deleteUser(id);

        return "redirect:/user/show";
    }

    @GetMapping("/update/{userID}")
    public String UpdateUser(Model model, @PathVariable(name = "userID") int id){
        Optional<UsersEntity> optionalUsersEntity = userService.geUsersEntityOptional(id);

        optionalUsersEntity.ifPresent(value -> {
            model.addAttribute("users", value);
        });
        System.out.println(optionalUsersEntity);
        List<RoleEntity> listRole = roleService.getAllRole();
        model.addAttribute("listRole",listRole);

        return "user-update";
    }

    @PostMapping("/update/{userID}")
    public String progressUpdateUser(Model model, @PathVariable(name = "userID") int id, @RequestParam String fullname,
                                     @RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String avatar, @RequestParam int idRole){
        //model
        List<RoleEntity> listRole = roleService.getAllRole();
        model.addAttribute("listRole",listRole);

        Optional<UsersEntity> optionalUsersEntity = userService.geUsersEntityOptional(id);

        optionalUsersEntity.ifPresent(value -> {
            model.addAttribute("users", value);
        });

        //logic
        UsersEntity updateUser = new UsersEntity();

        RoleEntity roleUser = roleService.getRolesEntityById(idRole);


        updateUser.setId(id);
        updateUser.setFullName(fullname);
        updateUser.setEmail(email);
        updateUser.setPassword(password);
        updateUser.setAvatar(avatar);
        updateUser.setRolesEntity(roleUser);

        boolean isSuccess = userService.updateUser(updateUser);

        System.out.println("Ket qua: "+isSuccess);

        return "user-update";
    }

    @GetMapping("/detail/{userID}")
    public String detailUser(Model model, @PathVariable(name = "userID") int id){
        UsersEntity users = userService.getUserByID(id);
        model.addAttribute("users", users);

        List<TaskEntity> tasks = taskService.getTaskByUserID(id);
        model.addAttribute("tasks", tasks);

        return "user-details";
    }

}
