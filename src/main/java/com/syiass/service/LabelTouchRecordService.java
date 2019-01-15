package com.syiass.service;

import com.syiass.repository.LabelTouchRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//事务处理放在service层
@Service
public class LabelTouchRecordService {


    @Autowired
    private LabelTouchRecordRepository labelTouchRecordRepository;

    @Transactional
    public int insertLabelTouchRecord(String label,String tfourmac,Long datetime){

        int i =labelTouchRecordRepository.insertLabelTouchRecord(label,tfourmac,datetime);

        return i;
    }



}



