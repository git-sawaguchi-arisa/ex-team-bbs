package com.example.controller;

import java.util.List;

import com.example.Service.ArticleService;
import com.example.domain.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team-bbs")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("")
    public String index(Model model){
        List<Article> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        return "html/form";
    }
}