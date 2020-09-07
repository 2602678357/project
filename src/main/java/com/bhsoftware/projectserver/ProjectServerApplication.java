package com.bhsoftware.projectserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages ="com.bhsoftware" )
@EnableCaching
public class ProjectServerApplication   {

    public static void main(String[] args) {
        SpringApplication.run(ProjectServerApplication.class, args);
    }


}
