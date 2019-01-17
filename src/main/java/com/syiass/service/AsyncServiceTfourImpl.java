package com.syiass.service;

import com.syiass.repository.MacPhoneModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service

public class AsyncServiceTfourImpl implements AsyncTfourService {


    @Autowired
    private MacPhoneModelRepository macPhoneModelRepository;

    @Autowired
    private TfourService tfourService;



    public static Set<String> sets =new HashSet<>();

    public static Set<String> getSets() {
        return sets;
    }

    public static void setSets(Set<String> sets) {
        AsyncServiceTfourImpl.sets = sets;
    }


    private static final Logger log = LoggerFactory.getLogger(AsyncTfourService.class);

    //每发送一次请求，进行去重mac并保存到数据库
    @Override
    public void executeAsync(String content) {

        log.info("start mac executeAsync");
        //处理标签type=probea or probeb
        long nowtime = System.currentTimeMillis()/1000;
        String[] contentsplits = content.split("sta=|&shop=|&time=|&type=|&data=");
        //String sta = contentsplits[0]; //空
        String sta = contentsplits[1];
        String unite = contentsplits[2];
        String type = contentsplits[3];
        String data = contentsplits[4];
        //存放在Map集合去重 mac+T4探针mac作为key值
        Map<String,String> map=new HashMap<>();

        String delimiter = "\1";//分隔符号为数字 1 不是字母 1,因此需要斜线转义
        int startp = data.indexOf(delimiter);//正确的数据以分隔符开始,如果第一个字母不是分隔符,就去除掉,从找到 的第一个分隔符开始

        if(startp!=-1)
        {
            //正确的数据以分隔符结束,如果最后一个字母不是分隔符,就去除掉,从最后一个分割符结束
            int endp = data.lastIndexOf(delimiter);
            log.info("处理mac地址 startp==="+startp+"----endp==="+endp);

            if(((endp-startp)-1)>12)//mac地址至少12位
            {
                String datatrimed = data.substring(startp+1, endp);//去除掉两边可能的脏数据
                String[] datasplits = datatrimed.split(delimiter);
                log.info("嗅探mac执行的时间--"+new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss").format(new Date()));
                for(int i=0;i<datasplits.length;i++)
                {
                    if(datasplits[i].length()>12)//风险防控,正确的数据长度肯定大于6
                    {
                        String mac=datasplits[i].substring(0, 12);

                        //每一段的前 12 个字符为 mac 地址 //转为 byte 处理,不同语言方法不同
                        byte[] datasplitbytes = datasplits[i].getBytes();
                        //从第 7 个 byte 开始,每一个 byte 表示该 mac 地址的一个 rssi 值
                        for(int j = 12;j<datasplitbytes.length;j++) {
                            int rssi=datasplitbytes[j];
                            log.info("-----mac=="+mac+"----rssi==="+rssi);

                            //空的变量放在右边
                            if(macPhoneModelRepository.findByMac(mac.substring(0, 6).toUpperCase())!=null && !"".equals(macPhoneModelRepository.findByMac(mac.substring(0, 6).toUpperCase()))){
                                //存放手机mac
                                //去重
                                map.put(mac+","+sta, mac+","+sta);
                            }

                            //正确的rssi, 进行处理即可,rssi应该是负数,
                            //但我们为了减少传输字节,用的是绝对值,rssi是一个9到99的数 值
                            if((rssi>9)&&(rssi<100)) {

                            }


                        }
                    }
                }
            }

        }
        if(!map.isEmpty()){
            //遍历map中的键  保存到list集合中
            for (String key : map.keySet()) {
                //System.out.println("--key="+map.get(key).substring(0,map.get(key).indexOf(","))+"---");
                tfourService.insertTfour(map.get(key).substring(map.get(key).indexOf(",")+1), map.get(key).substring(0,map.get(key).indexOf(",")).toUpperCase(),nowtime);
            }
        }
        //每次触发，处理完成清空map
        map.clear();

        log.info("end mac executeAsync");

    }
}
