package com.feng.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by rf on 2019/4/3.
 */
@Data
@Table(name = "tb_blog")
@Entity(name = "blog")
public class Blog {
    @Id
    @GeneratedValue
    @Column(name = "id", length = 32)
    private Integer id;
    private String title;
    private String content;
    @Column(length = 50)
    private String publishTime;
    @Column(length = 50)
    private String sourceUrl;

    public Blog(String title, String content, String publishTime, String sourceUrl) {
        this.title = title;
        this.content = content;
        this.publishTime = publishTime;
        this.sourceUrl = sourceUrl;
    }

    public Blog() {
    }
}