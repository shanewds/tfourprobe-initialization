package com.syiass.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/*

    每次提交线程的时候都会将当前线程池的运行状况打印出来
    线程池中有多少线程在执行，多少在队列中等待

 */
public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    /*


2019-01-15 10:19:16.706  INFO 970 --- [nio-8083-exec-5] c.s.log.VisiableThreadPoolTaskExecutor   : async-service-, 2. do submit,taskCount [4], completedTaskCount [4], activeCount [0], queueSize [0]
2019-01-15 10:19:16.706  INFO 970 --- [async-service-5] com.syiass.service.AsyncServiceImpl      : start executeAsync
异步线程要做的事情
2019-01-15 10:19:16.706  INFO 970 --- [async-service-5] com.syiass.service.AsyncServiceImpl      : end executeAsync

async-service-, 2. do submit,taskCount [4], completedTaskCount [4], activeCount [0], queueSize [0]
这说明提交任务到线程池的时候，调用的是submit(Callable task)这个方法，
当前已经提交了4个任务，完成了4个，当前有0个线程在处理任务，还剩0个任务在队列中等待，线程池的基本情况一路了然；


     */
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolTaskExecutor.class);


    private void showThreadPoolInfo(String prefix) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();


        if (null == threadPoolExecutor) {
            return;
        }

        log.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                this.getThreadNamePrefix(),
                prefix,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

    //方法一
    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("1. do execute");
        super.execute(task);
    }
    //方法二
    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("2. do execute");
        super.execute(task, startTimeout);
    }
    //方法三
    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("1. do submit");
        return super.submit(task);
    }
   ///调用的是submit(Callable task)这个方法
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("2. do submit");
        return super.submit(task);
    }

}
