package com.syiass.repository;

import com.syiass.domain.TagInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TagInfoRepository extends Repository<TagInfo,Integer> {


   //获取所有的表数据
   @Query("select t from TagInfo t")
   public List<TagInfo> getAllList();




}
