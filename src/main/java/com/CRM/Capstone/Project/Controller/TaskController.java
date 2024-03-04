package com.CRM.Capstone.Project.Controller;

import com.CRM.Capstone.Project.Entity.*;
import com.CRM.Capstone.Project.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private StatusService statusService;

    @GetMapping("")
    public String task(Model model){
        List<TaskEntity> listTask = taskService.getAllTask();
        model.addAttribute("listTask", listTask);

        return "task";
    }


    @GetMapping("/add")
    public String taskAdd(Model model){
        List<JobsEntity> listJob = jobService.getAllJobs();
        model.addAttribute("listJob", listJob);

        String roleName = "ROLE_USER";
        RoleEntity role = roleService.getRoleByName(roleName);
        List<UsersEntity> listUser = userService.getAllUserByRoleID(role.getId());
        model.addAttribute("listUser", listUser);

        return "task-add";
    }

    @PostMapping("/add")
    public String progressAddTask(Model model,@RequestParam int jobID, @RequestParam String taskName,
                                  @RequestParam int userID, @RequestParam Date startDate,
                                  @RequestParam Date endDate){
        UsersEntity usersEntity = userService.getUserByID(userID);
        JobsEntity jobsEntity = jobService.getJobByID(jobID);
        StatusEntity statusEntity = statusService.getStatusByID(1);

        boolean isSucess = taskService.addTask(taskName,startDate,endDate,usersEntity, jobsEntity, statusEntity);

        System.out.println(isSucess);
        return "task-add";
    }

    @GetMapping("/delete/{id}")
    private String deleteTask(@PathVariable(name = "id")int id){
        boolean isSuccess = taskService.deleteTask(id);
        System.out.println("Delete "+isSuccess);
        return "redirect:/task";
    }
}