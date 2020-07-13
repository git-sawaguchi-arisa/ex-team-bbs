package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.Service.ArticleService;
import com.example.Service.CommentService;
import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team-bbs")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private CommentService commentService;
    
    @ModelAttribute
    public ArticleForm setUpForm() {
    	return new ArticleForm();
    }
    
    @RequestMapping("")
    public String index(Model model){
        List<Article> articleList = articleService.findAll();
        for (Article article : articleList) {
			if (Objects.nonNull(commentService.findByArticleId(article.getId()))) {
				List<Comment> commentList = commentService.findByArticleId(article.getId());
				article.setCommentList(commentList);
			} else {
				List<Comment> commentList = new ArrayList<Comment>();
				article.setCommentList(commentList);
			}
		}
        model.addAttribute("articleList", articleList);
        return "html/form";
    }
    @RequestMapping("/delete")
    public String deleteArticle(int id) {
 	   commentService.deleteByArticleId(id);
 	   articleService.deleteById(id);
 	   return "redirect:/team-bbs";
    }
}