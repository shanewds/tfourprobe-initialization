package com.syiass.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelTouchRecordServiceTest {

    @Autowired
    private  LabelTouchRecordService labelTouchRecordService;

    @Test
    public void insertLabelTouchRecord() {

        int i =labelTouchRecordService.insertLabelTouchRecord("be0000","5ccf7fbb7a3f",System.currentTimeMillis()/1000);
        System.out.println("i==="+i);
    }
}