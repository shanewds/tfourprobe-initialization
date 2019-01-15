package com.syiass.repository;

import com.syiass.domain.MacPhoneModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MacPhoneModelRepositoryTest {

    @Autowired
    private MacPhoneModelRepository macPhoneModelRepository;

    @Test
    public void findByMac() {
        //001302
        MacPhoneModel macPhoneModel = macPhoneModelRepository.findByMac("001302");
        if (!StringUtils.isEmpty(macPhoneModel)){
            System.out.println(macPhoneModel);
        }

    }
}