package com.CRM.Capstone.Project.Filter;


import com.CRM.Capstone.Project.Entity.UsersEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String path = request.getServletPath();

        System.out.println("Kich hoat filter phan quyen");

        if(session!=null && session.getAttribute("loginSession")!=null){

            UsersEntity userSession = (UsersEntity) session.getAttribute("loginSession");
            String role = userSession.getRolesEntity().getName();

            if(role.equalsIgnoreCase("role_admin")){
                filterChain.doFilter(servletRequest,servletResponse);
            }else if(role.equalsIgnoreCase("role_manager")){
                if(path.startsWith("/role")){
                    response.sendRedirect("/404");
                }else {
                    filterChain.doFilter(servletRequest,servletResponse);
                }
            } else if(role.equalsIgnoreCase("role_user")){
                if(path.startsWith("/profile")){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    response.sendRedirect("/404");
                }
            }
        }else {
            response.sendRedirect("/login");
        }

    }
}
