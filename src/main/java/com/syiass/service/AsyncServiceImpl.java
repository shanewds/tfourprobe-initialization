package com.syiass.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {


    /*
       将Service层的服务异步化，
       在executeAsync()方法上增加注解@Async("asyncServiceExecutor")，
       asyncServiceExecutor方法是前面ExecutorConfig.java中的方法名
       表明executeAsync方法进入的线程池是asyncServiceExecutor方法创建的
     */
    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {

         log.info("start executeAsync");
         System.out.println("异步线程要做的事情");

         log.info("end executeAsync");

    }
}
