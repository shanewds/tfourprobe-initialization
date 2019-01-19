package com.syiass.controller;

import com.syiass.service.AsyncService;
import com.syiass.service.AsyncTfourService;
import com.syiass.util.HttpServletRequestReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.IOException;

@RestController
public class AsyncController {


    @Autowired
    private AsyncService asyncService;

    @Autowired
    private AsyncTfourService asyncTfourService;


    private static final Logger log = LoggerFactory.getLogger(AsyncController.class);

    /*
      通过以上日志可以发现，[async-service-]是有多个线程的，显然已经在我们配置的线程池中执行了，
      并且每次请求中，controller的起始和结束日志都是连续打印的，表明每次请求都快速响应了，
      而耗时的操作都留给线程池中的线程去异步执行；
     */
//    @GetMapping("/async")
//    public void async(String content){
//
//
//       asyncService.executeAsync(content);
//
//
//    }


    //@GetMapping 请求方式Get
    //接收POST请求 PostMapping
    //@PostMapping("/data/upload3")
    @RequestMapping("/data/upload3")
    public  String dataupload3(HttpServletRequest request, HttpServletResponse response) {
        log.info("---T4探针发送数据--");
        try {
            String content = HttpServletRequestReader.doPost(request,response);
            System.out.println("content=="+content);

            if((content==null)||(content.length()>1600)||(!content.startsWith("sta="))){
                return "ok";
            }else{
                //sta=&
                String[] contentsplits = content.split("sta=|&shop=|&time=|&type=|&data=");
                //String sta = contentsplits[0]; //空
                String sta = contentsplits[1];
                String unite = contentsplits[2];
                String type = contentsplits[3];
                String data = contentsplits[4];
//	            System.out.println("s="+unite);
//	    		System.out.println("sta==="+sta);
//	    		System.out.println("type==="+type);
//	    		System.out.println("data==="+data);
//	    		System.out.println("data's length=="+data.length());

                if((sta==null)||(type==null)||(data==null)){
                    return "ok";
                }else{
                    if(type.equals("ap"))
                    {
                        //处理ap扫描结果
                        return "ok";
                    }

                    if(type.equals("configa")) {
                        //处理wifi 上报配臵信息
                        return "ok";

                    }

                    if(type.equals("check")) {

                        return "ok";
                    }
                    // wifi a 芯片
                    boolean probeatrue = false;
                    if(type.equals("probea"))
                    {
                        probeatrue = true;
                    }
                    // wifi b 芯片
                    boolean probebtrue = false;
                    if(type.equals("probeb"))
                    {
                        probebtrue = true;
                    }

                    //只处理WiFi a 芯片的嗅探的mac
                    if(probeatrue) {
                        System.out.println("---处理上报探测到的mac信息--");
                        //线程池处理
                        //asyncTfourService.executeAsync(content);
                        return "ok";
                    }


                    if(type.equals("tag")) {
                        log.info("---处理标签--");
                        //线程池处理
                        //处理tag标签
                        asyncService.executeAsync(content);
                        return "ok";


                    }

                    if(type.equals("register")) {

                        //处理上报的mac与个人信息映射
                        return "ok";

                    }

                }
            }

        } catch (Exception e) {

            e.printStackTrace();


        }

         return "ok";
    }






}
