package com.syiass.controller;

import com.syiass.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {


    @Autowired
    private AsyncService asyncService;

    /*
2019-01-15 06:57:32.201  INFO 1730 --- [async-service-2] com.syiass.service.AsyncServiceImpl      : start executeAsync
异步线程要做的事情
2019-01-15 06:57:32.202  INFO 1730 --- [async-service-2] com.syiass.service.AsyncServiceImpl      : end executeAsync
2019-01-15 06:57:32.841  INFO 1730 --- [async-service-3] com.syiass.service.AsyncServiceImpl      : start executeAsync
异步线程要做的事情
2019-01-15 06:57:32.841  INFO 1730 --- [async-service-3] com.syiass.service.AsyncServiceImpl      : end executeAsync
2019-01-15 06:57:33.194  INFO 1730 --- [async-service-4] com.syiass.service.AsyncServiceImpl      : start executeAsync
异步线程要做的事情
2019-01-15 06:57:33.194  INFO 1730 --- [async-service-4] com.syiass.service.AsyncServiceImpl      : end executeAsync
2019-01-15 06:57:33.550  INFO 1730 --- [async-service-5] com.syiass.service.AsyncServiceImpl      : start executeAsync
异步线程要做的事情
2019-01-15 06:57:33.551  INFO 1730 --- [async-service-5] com.syiass.service.AsyncServiceImpl      : end executeAsync
2019-01-15 06:57:33.857  INFO 1730 --- [async-service-1] com.syiass.service.AsyncServiceImpl      : start executeAsync
异步线程要做的事情
2019-01-15 06:57:33.857  INFO 1730 --- [async-service-1] com.syiass.service.AsyncServiceImpl      : end executeAsync
     */
    /*
      通过以上日志可以发现，[async-service-]是有多个线程的，显然已经在我们配置的线程池中执行了，
      并且每次请求中，controller的起始和结束日志都是连续打印的，表明每次请求都快速响应了，
      而耗时的操作都留给线程池中的线程去异步执行；
     */
    @GetMapping("/async")
    public void async(){
        asyncService.executeAsync();
    }
}
