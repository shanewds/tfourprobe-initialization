package com.syiass.service;

import com.syiass.repository.TfourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TfourService {

    @Autowired
    private TfourRepository tfourRepository;

    @Transactional
    public int insertTfour(String tfourmac,String mac,Long datetime){

        int i = tfourRepository.insertTfour(tfourmac,mac,datetime);
        return i;

    }


}
