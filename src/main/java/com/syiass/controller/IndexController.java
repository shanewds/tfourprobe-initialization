package com.syiass.controller;

import com.syiass.domain.TagInfo;
import com.syiass.ehcache.TaginfoEhcache;
import com.syiass.repository.TagInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private TagInfoRepository tagInfoRepository;

    @Autowired
    private TaginfoEhcache taginfoEhcache;

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);



    @GetMapping("/index")
    public Object index(){

        List<TagInfo> tagInfoList =  taginfoEhcache.getAllList();
        log.error("错误信息====");
        log.warn("警告信息====");
        log.info("info===="+tagInfoList.toString());
        log.debug("debug调试====");
        return  taginfoEhcache.getAllList();



    }
}
