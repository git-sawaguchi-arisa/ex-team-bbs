package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.Service.ArticleService;
import com.example.Service.CommentService;
import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;

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
    public ArticleForm setUpArticleForm() {
    	return new ArticleForm();
    }
    @ModelAttribute
    public CommentForm setUpCommentForm(){
        return new CommentForm();
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
    @RequestMapping("/articleInsert")
	public String insertArticle(ArticleForm form,Model model) {
		Article article=new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleService.insert(article);
		return index(model);
    }
    @RequestMapping("/commentInsert")
	public String insertComment(CommentForm form, Model model) {
		Comment comment=new Comment();
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		comment.setArticleId(Integer.parseInt(form.getArticleId()));
		commentService.insert(comment);
		return index(model);
	}
}