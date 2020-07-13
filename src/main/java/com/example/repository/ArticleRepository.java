package com.example.repository;

import java.util.List;

import com.example.domain.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Article> ARTICLE_ROW_Mapper = (rs,i) ->{
        Article article =new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        return article;
    };

    public List<Article> findAll(){
       String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";
       try {
        List<Article> articleList = template.query(sql, ARTICLE_ROW_Mapper);
        return articleList;
       } catch (Exception e) {
           //TODO: handle exception
           return null;
       }
        
    }
    public void insert(Article article) {
		String sql="INSERT INTO articles(name, content) VALUES(:name,:content)";
		SqlParameterSource  param=new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
		template.update(sql, param);
	}
    public void deleteById(int id) {
   	   String deleteSql = "DELETE FROM articles WHERE id = :id";
   	   SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
   	   template.update(deleteSql, param);
      }
}
