package com.syiass.ehcache;

import com.syiass.domain.TagInfo;
import com.syiass.repository.TagInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

//标注数据访问组件,即DAO组件
/*
     参数 value缓存的名称必须指定至少一个
          缓存的key可以为空
     key 定义的id是传入的数值
     @Cacheable(value=”testcache”,key=”#id”)
 */

// cacheNames为ehcache.xml配置文件内的配置名称
@CacheConfig(cacheNames = {"table1Cache"})
@Repository
public class TaginfoEhcache {


    @Autowired
    private TagInfoRepository tagInfoRepository;


    /*
         @Cacheable：应用到读取数据的方法上，即可缓存的方法，
         如查找方法：先从缓存中读取，如果没有再调用方法获取数据，
         然后把数据添加到缓存中，适用于查找；
         value 指的是 ehcache.xml 中的缓存策略 既name的数值
     */
    @Cacheable(value= "table1Cache")
    public List<TagInfo> getAllList(){

        return  tagInfoRepository.getAllList();

    }



}
