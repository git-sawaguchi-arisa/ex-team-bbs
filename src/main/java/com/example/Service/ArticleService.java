package com.example.Service;

import java.util.List;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    public List<Article> findAll() {
        List<Article> articleList = articleRepository.findAll();
        return articleList;
    }
    public void insert(Article article) {
        articleRepository.insert(article);
    }
    public void deleteById(int id) {
		articleRepository.deleteById(id);
	}
}
