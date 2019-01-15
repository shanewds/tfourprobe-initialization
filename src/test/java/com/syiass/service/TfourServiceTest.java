package com.syiass.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TfourServiceTest {

    @Autowired
    private TfourService tfourService;

    @Test
    public void insertTfour() {

        int i = tfourService.insertTfour("5ccf7fbb7a3f","2C0E3D458834",System.currentTimeMillis()/1000);
        Assert.assertNotEquals(0,i);


    }
}