package com.syiass.service;

import com.syiass.domain.TagInfo;
import com.syiass.ehcache.TaginfoEhcache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AsyncServiceImpl implements AsyncService {


    @Autowired
    private LabelTouchRecordService labelTouchRecordService;

    @Autowired
    private TaginfoEhcache taginfoEhcache;


    //统计标签碰撞的次数
    public static List<String> list = new ArrayList<>();

    public static List<String> getList() {
        return list;
    }

    public static void setList(List<String> list) {
        AsyncServiceImpl.list = list;
    }

    private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

    /*
       将Service层的服务异步化，
       在executeAsync()方法上增加注解@Async("asyncServiceExecutor")，
       asyncServiceExecutor方法是前面ExecutorConfig.java中的方法名
       表明executeAsync方法进入的线程池是asyncServiceExecutor方法创建的
     */
    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(String content) {

        log.info("start tag executeAsync");


        //处理标签type=tag
        long time = System.currentTimeMillis() / 1000;
        String[] contentsplits = content.split("sta=|&shop=|&time=|&type=|&data=");
        //String sta = contentsplits[0]; //空
        String sta = contentsplits[1];
        String unite = contentsplits[2];
        String type = contentsplits[3];
        String data = contentsplits[4];
        //log.info("sta===" + sta);
        //从数据库或者缓冲中，获取触控标签的集合
        List<TagInfo> taginfos = taginfoEhcache.getAllList();
        log.info("taginfos=" + taginfos);

        //先放入map集合去重，再保存到list集合中
        Map<String, String> map = new HashMap<>();

        //只处理tag标签
        String delimiter = "\1";//分隔符号为数字 1 不是字母 1,因此需要斜线转义
        int startp = data.indexOf(delimiter);//正确的数据以分隔符开始,如果第一个字母不是分隔符,就去除掉,从找到 的第一个分隔符开始
        log.info("startp=" + startp);
        if (startp != -1) {
            //正确的数据以分隔符结束,如果最后一个字母不是分隔符,就去除掉,从最后一个分割符结束
            int endp = data.lastIndexOf(delimiter);
            log.info("startp===" + startp + "----endp===" + endp);
            if (((endp - startp) - 1) > 6)//因为商品标签ID为六位,加上至少一个 rssi,因此长度要大于6
            {
                String datatrimed = data.substring(startp + 1, endp);//去除掉两边可能的脏数据
                String[] datasplits = datatrimed.split(delimiter);
                log.info("标签执行的时间--" + new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss").format(new Date()));
                for (int i = 0; i < datasplits.length; i++) {
                    if (datasplits[i].length() > 6)//风险防控,正确的数据长度肯定大于 6
                    {
                        String mac = datasplits[i].substring(0, 6);

                        //每一段的前 6个字符为标签 //转为 byte 处理,不同语言方法不同
                        byte[] datasplitbytes = datasplits[i].getBytes();
                        //从第 6 个 byte 开始,每一个 byte 表示该 mac 地址的一个 rssi 值
                        for (int j = 6; j < datasplitbytes.length; j++) {

                            int rssi = datasplitbytes[j];
                            log.info("标签mac==" + mac + "----标签rssi===" + rssi);
                            //触碰次数的计算优化  标签编号从数据库中获取
                            //遍历list集合
                            // 暂时通过范围进行区分--触碰标签只向一个T4探针发送(其实T4探针无法和触控标签进行绑定)
                            for (int t = 0; t < taginfos.size(); t++) {
                                if (mac.equals(taginfos.get(t).getNumber())) {
                                    map.put(mac + "," + sta, mac + "," + sta);
                                }
                            }

                            //正确的rssi, 进行处理即可,rssi应该是负数,
                            //但我们为了减少传输字节,用的是绝对值,rssi是一个9到99的数 值
                            if ((rssi > 9) && (rssi < 100)) {

                            }
                        }
                    }
                }
            }
        }

        if (!map.isEmpty()) {
            //遍历map中的键
            for (String key : map.keySet()) {
                //list.add(key);
                //实时保存数据到数据库，记录每一触控标签的时间戳
                //label,   tfourmac,   datetime
                labelTouchRecordService.insertLabelTouchRecord(map.get(key).substring(0, map.get(key).indexOf(",")), map.get(key).substring(map.get(key).indexOf(",") + 1), time);

            }
        }

        log.info("end tag executeAsync");
    }



 }





