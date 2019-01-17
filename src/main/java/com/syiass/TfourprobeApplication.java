package com.syiass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

//@SpringBootApplication表明是一个Spring Boot应用
@SpringBootApplication
//启动缓冲注解
@EnableCaching
public class TfourprobeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TfourprobeApplication.class, args);
    }

}

