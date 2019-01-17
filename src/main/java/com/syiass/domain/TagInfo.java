package com.syiass.domain;

import lombok.*;

import javax.persistence.*;

//先开发实体类，自动生成表 （当运行springboot启动类的时候，自动创建表）
//此处不能使用lombok注解，原因再打包时AsyncServiceImpl类引用此get方法找不到符号的错误
@Entity
public class TagInfo {

     //设置自增 主键由数据库自动生成
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     //设置列名长度以及不允许为空
     @Column(length = 20,nullable = false)
     private String number;

     @Column(nullable = false)
     private Integer tanzheninfoid;


     @Override
     public String toString() {
          return "TagInfo{" +
                  "id=" + id +
                  ", number='" + number + '\'' +
                  ", tanzheninfoid=" + tanzheninfoid +
                  '}';
     }

     public TagInfo() {
     }

     public TagInfo(String number, Integer tanzheninfoid) {
          this.number = number;
          this.tanzheninfoid = tanzheninfoid;
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
     }

     public String getNumber() {
          return number;
     }

     public void setNumber(String number) {
          this.number = number;
     }

     public Integer getTanzheninfoid() {
          return tanzheninfoid;
     }

     public void setTanzheninfoid(Integer tanzheninfoid) {
          this.tanzheninfoid = tanzheninfoid;
     }



}
