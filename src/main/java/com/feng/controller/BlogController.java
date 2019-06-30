package com.feng.controller;

import com.feng.entity.Blog;
import com.feng.servcie.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by rf on 2019/6/16.
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private  BlogService blogService;

    @GetMapping("/list.htm")
    public String blogList(Blog search, Model model, @RequestParam(defaultValue = "1") int num,@RequestParam(defaultValue = "8") int size) {
        PageInfo<Blog> blogPage = blogService.ListPage(search,num,size);
        model.addAttribute("blogPage",blogPage);
        return "blog/list";
    }

    @GetMapping("/blog.htm")
    public String test() {
        return "blog/detail";
    }
}
