package com.syiass.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tfour {

    //设置自增 主键由数据库自动生成
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //设置列名长度以及不允许为空
    @Column(length = 20,nullable = false)
    private String tfourmac;

    @Column(length = 20,nullable = false)
    private String mac;

    @Column(nullable = false)
    private Long datetime;

}
