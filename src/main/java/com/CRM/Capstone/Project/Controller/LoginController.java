package com.CRM.Capstone.Project.Controller;

import com.CRM.Capstone.Project.Entity.UsersEntity;
import com.CRM.Capstone.Project.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String login(Model model, HttpSession httpSession){
        if(httpSession.getAttribute("loginSession") != null){
            UsersEntity loginSession = (UsersEntity) httpSession.getAttribute("loginSession");
            model.addAttribute("emailSession", loginSession.getEmail());
            model.addAttribute("passwordlSession", loginSession.getPassword());
        }
        return "login";
    }

    @PostMapping("")
    public String progressLogin(Model model, HttpServletResponse response, HttpSession httpSession,
                                HttpServletRequest request, @RequestParam String email,
                                @RequestParam String password, @RequestParam(required = false) boolean remember){

        List<UsersEntity> users = userService.getUserByEmailAndPassword(email,password);

        boolean isSuccess = !users.isEmpty();

        model.addAttribute("isSuccess", isSuccess);

        if(!isSuccess){
            return "login";
        }else{
            UsersEntity loginSession = users.get(0);
            httpSession.setAttribute("loginSession", loginSession);
            httpSession.setMaxInactiveInterval(8 * 60 * 60);
            return "redirect:/dashboard";
        }
    }
}
