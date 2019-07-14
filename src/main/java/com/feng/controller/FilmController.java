package com.feng.controller;

import com.feng.entity.Film;
import com.feng.servcie.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rf on 2019/6/16.
 */
@Controller
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private  FilmService filmService;

    @GetMapping("/list.htm")
    public String filmList(Film search, Model model, @RequestParam(defaultValue = "1") int num,@RequestParam(defaultValue = "24") int size) {
        Page<Film> filmPage = filmService.ListPage(search,num,size);
        model.addAttribute("filmPage",filmPage);
        return "film/list";
    }

}
