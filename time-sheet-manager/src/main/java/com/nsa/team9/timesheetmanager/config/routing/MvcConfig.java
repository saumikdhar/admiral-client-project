package com.nsa.team9.timesheetmanager.config.routing;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  public void addViewControllers(ViewControllerRegistry registry) {

    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/403").setViewName("403");

//    registry.addViewController("/reports").setViewName("forward:/reports/index.html");
    registry.addViewController("/manager").setViewName("forward: /manager");
    registry.addViewController("/admin").setViewName("forward: /admin/timesheets");

  }

}
