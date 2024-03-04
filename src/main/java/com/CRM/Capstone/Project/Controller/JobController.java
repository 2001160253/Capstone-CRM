package com.CRM.Capstone.Project.Controller;

import com.CRM.Capstone.Project.Entity.*;
import com.CRM.Capstone.Project.Service.JobService;
import com.CRM.Capstone.Project.Service.TaskService;
import com.CRM.Capstone.Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/groupwork")
public class JobController {
    @Autowired
    private JobService jobService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String groupwork(Model model){
        List<JobsEntity> listJob = jobService.getAllJobs();
        model.addAttribute("listJob", listJob);

        System.out.println(listJob);

        return "groupwork";
    }

    @GetMapping("/add")
    public String add(){
        return "groupwork-add";
    }

    @PostMapping("/add")
    public String progressAddJob(@RequestParam String jobName, @RequestParam Date startDate, @RequestParam Date endDate){
        try {
            boolean isSucess = jobService.addJob(jobName, startDate, endDate);
            System.out.println("Success: "+isSucess);
        }catch (Exception e){
            System.out.println("Errorr: "+e.getMessage());
        }
        return "groupwork-add";
    }

    @GetMapping("/delete/{id}")
    public String progressDeleteJob(@PathVariable(name = "id")int id){
        jobService.deleteJob(id);
        System.out.println(id);
        return "redirect:/groupwork";
    }

    @GetMapping("/update/{id}")
    public String updateGroupwork(@PathVariable(name = "id")int id, Model model){
        JobsEntity job = jobService.getJobByID(id);
        model.addAttribute("job",job);

        System.out.println(job);
        return "groupwork-update";
    }

    @PostMapping("/update/{id}")
    public String progressUpdateJob(Model model, @PathVariable(name = "id")int id, @RequestParam String jobName,
                                    @RequestParam Date startDate, @RequestParam Date endDate){

        JobsEntity jobsEntity = jobService.getJobByID(id);
        jobsEntity.setName(jobName);
        jobsEntity.setStartDate(startDate);
        jobsEntity.setEndDate(endDate);

        boolean isSuccess = jobService.updateJob(jobsEntity);

        return "redirect:/groupwork";
    }

    @GetMapping("/detail/{id}")
    public String progressDetailJob(@PathVariable(name = "id")int id, Model model){
        List<TaskEntity> tasks = taskService.getTaskByJobID(id);
        model.addAttribute("tasks", tasks);

        return "groupwork-details";
    }
}
