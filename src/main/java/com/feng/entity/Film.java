package com.feng.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by rf on 2019/4/3.
 */
@Data
@Entity(name = "tb_film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 32)
    private Long id;
    @Column(length = 120)
    private String title;
    @Column(length = 170)
    private String url;
    @Column(length = 180)
    private String imgUrl="";
    @Column
    private String score;
    @Column
    private String urlSource="";
    @Column
    private String type="";
    @Column
    private String description="";
    @Column
    private String actor="";


    public Film(String title, String url, String imgUrl, String score) {
        this.title = title;
        this.url = url;
        this.imgUrl = imgUrl;
        this.score = score;
    }

    public Film() {
    }
}
