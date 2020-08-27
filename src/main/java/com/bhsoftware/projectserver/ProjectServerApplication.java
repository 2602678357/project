package com.bhsoftware.projectserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ="com.bhsoftware" )
public class ProjectServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectServerApplication.class, args);
    }

}
