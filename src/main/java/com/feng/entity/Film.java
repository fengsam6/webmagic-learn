package com.feng.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by rf on 2019/4/3.
 */
@Data
@Entity(name = "tb_film")
public class Film {
    @Id
    @GeneratedValue
    @Column(name = "id", length = 32)
    private Integer id;
    @Column(length = 70)
    private String title;
    @Column(length = 70)
    private String url;
    @Column(length = 70)
    private String imgUrl;
    private String score;


    public Film(String title, String url, String imgUrl, String score) {
        this.title = title;
        this.url = url;
        this.imgUrl = imgUrl;
        this.score = score;
    }

    public Film() {
    }
}
