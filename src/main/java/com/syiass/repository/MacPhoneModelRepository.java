package com.syiass.repository;

import com.syiass.domain.MacPhoneModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Table;

public interface MacPhoneModelRepository extends Repository<MacPhoneModel,Integer> {


    @Query("select m from MacPhoneModel m where m.mac=:mac")
    public MacPhoneModel findByMac(@Param("mac") String mac);


}
