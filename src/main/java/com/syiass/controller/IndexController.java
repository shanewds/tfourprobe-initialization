package com.syiass.controller;

import com.syiass.repository.TagInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private TagInfoRepository tagInfoRepository;


    @GetMapping("/index")
    public Object index(){


        return tagInfoRepository.getAllList();


    }
}
