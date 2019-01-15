package com.syiass.repository;

import com.syiass.domain.Tfour;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface TfourRepository extends Repository<Tfour,Integer> {


    //原生sql进行插入数据
    @Modifying
    @Query(value = "insert into tfour(tfourmac,mac,datetime) values(:tfourmac,:mac,:datetime)",nativeQuery = true)
    public int insertTfour(@Param("tfourmac") String tfourmac,
                           @Param("mac") String mac,
                           @Param("datetime") Long datetime);

}
