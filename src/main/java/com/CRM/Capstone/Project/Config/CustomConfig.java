package com.CRM.Capstone.Project.Config;

import com.CRM.Capstone.Project.Filter.LoginFilter;
import com.CRM.Capstone.Project.Filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {
    @Bean
    public FilterRegistrationBean<LoginFilter>loginFilterFilterRegistrationBean(){
        FilterRegistrationBean<LoginFilter> registrationBean =new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/login");
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> roleFilterFilterRegistrationBean(){
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/role/*","/user/*","/groupwork/*","/task/*","/profile/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }

}
