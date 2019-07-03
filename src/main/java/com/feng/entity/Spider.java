package com.feng.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "tb_spider")
public class Spider {
    @GeneratedValue
    @Id
    private int id;
    private String url;
    private String spiderTime;

}
