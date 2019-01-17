package com.syiass.controller;

import com.syiass.repository.TagInfoRepository;
import com.syiass.service.AsyncService;
import com.syiass.service.AsyncTfourService;
import com.syiass.service.LabelTouchRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;

@RestController
public class AsyncController {


    @Autowired
    private AsyncService asyncService;

    @Autowired
    private AsyncTfourService asyncTfourService;

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

    @GetMapping("/data/upload3")
    public  String dataupload3(HttpServletRequest request){
        System.out.println("---触发请求--");

        //spring线程池处理高并发请求
        DataInputStream in=null;
        int len = request.getContentLength();
        byte[] contentbytes = new byte[len];
        String content=null;
        try {
            in = new DataInputStream(request.getInputStream());
            in.readFully(contentbytes);
            content = new String(contentbytes,"utf-8");
            System.out.println("content="+content);
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


                long timelong = System.currentTimeMillis();
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
                        asyncTfourService.executeAsync(content);
                        return "ok";
                    }


                    if(type.equals("tag")) {
                        System.out.println("---处理标签--");
                        //线程池处理
                        //处理tag标签
                        asyncService.executeAsync(content);
                        return "ok";


                    }

                    if(type.equals("register")) {

                        //处理上报的mac与个人信息映射
                        return "ok";


                    }

                }}


            return "ok";

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return "ok";

    }







}
