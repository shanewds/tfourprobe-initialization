package com.syiass.repository;

import com.syiass.domain.LabelTouchRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface LabelTouchRecordRepository extends Repository<LabelTouchRecord,Integer> {


       //原生sql进行插入数据
       @Modifying
       @Query(value = "insert into label_touch_record(label,tfourmac,datetime) values(:label,:tfourmac,:datetime)",nativeQuery = true)
       public int insertLabelTouchRecord(@Param("label") String label,
                                         @Param("tfourmac") String tfourmac,
                                         @Param("datetime") Long datetime);


}
