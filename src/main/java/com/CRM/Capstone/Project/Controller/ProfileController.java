package com.CRM.Capstone.Project.Controller;

import com.CRM.Capstone.Project.Entity.StatusEntity;
import com.CRM.Capstone.Project.Entity.TaskEntity;
import com.CRM.Capstone.Project.Entity.UsersEntity;
import com.CRM.Capstone.Project.Service.StatusService;
import com.CRM.Capstone.Project.Service.TaskService;
import com.CRM.Capstone.Project.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String profile(Model model, HttpServletRequest request){
       HttpSession httpSession = request.getSession();

        UsersEntity loginValue = (UsersEntity) httpSession.getAttribute("loginSession");
        model.addAttribute("loginValue",loginValue);
        if(loginValue != null){
            List<TaskEntity> listTask = taskService.getTaskByUserID(loginValue.getId());
            model.addAttribute("tasks", listTask);
        }

        return "profile";
    }

    @GetMapping("/edit/task/{idTask}")
    public String profileEdit(Model model, @PathVariable(name = "idTask")int id
                              ){
        Optional<TaskEntity> task = taskService.getTaskByTaskID(id);
        task.ifPresent(value -> {
            model.addAttribute("task", value);
        });

        List<StatusEntity> listStatus = statusService.getAllStatus();
        model.addAttribute("listStatus", listStatus);

        return "profile-edit";
    }

    @PostMapping("/edit/task/{idTask}")
    public String progressProfileEdit(Model model, @RequestParam int statusID, @PathVariable(name = "idTask")int taskID){

        Optional<TaskEntity> task = taskService.getTaskByTaskID(taskID);
        task.ifPresent(value -> {
            model.addAttribute("task", value);

            StatusEntity status = statusService.getStatusByID(statusID);
            value.setStatusEntity(status);

            boolean isSuccess = taskService.updateProfile(value);
        });

        return "profile-edit";
    }
}
