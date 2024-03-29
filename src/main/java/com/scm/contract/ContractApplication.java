package com.scm.contract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
public class ContractApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractApplication.class, args);
    }

}
