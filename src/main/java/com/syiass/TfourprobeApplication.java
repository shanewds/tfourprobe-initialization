package com.syiass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//开启servlet注解
//@ServletComponentScan
public class TfourprobeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TfourprobeApplication.class, args);
    }

}

