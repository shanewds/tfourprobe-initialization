package com.syiass.domain;

import lombok.*;

import javax.persistence.*;

//先开发实体类，自动生成表 （当运行springboot启动类的时候，自动创建表）
//lombok注解
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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


}
