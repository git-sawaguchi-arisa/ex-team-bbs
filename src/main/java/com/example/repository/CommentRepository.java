package com.example.repository;

import java.util.List;

import com.example.domain.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;
    
    private static final RowMapper<Comment> COMMENT_ROW_Mapper = (rs,i)->{
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getInt("article_id"));
        return comment;
    };

    public List<Comment> findByArticleId(Integer articleId) {
        String sql="SELECT id,name,content,article_id FROM comments WHERE article_id=:articleId ORDER BY id DESC";
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        try {
            List<Comment> commentList = template.query(sql,param, COMMENT_ROW_Mapper);
            return commentList;
        } catch (Exception e) {
            //TODO: handle exception
            return null;
        }
        
    }
    public void insert(Comment comment) {
		String sql="INSERT INTO comments(name,content,article_id) VALUES(:name,:content,:articleId)";
		SqlParameterSource param=new MapSqlParameterSource().addValue("name", comment.getName()).addValue("content", comment.getContent()).addValue("articleId", comment.getArticleId());
		template.update(sql, param);
	}
    public void deleteByArticleId(int articleId) {
 	   String deleteSql = "DELETE FROM comments WHERE article_id = :articleId";
 	   SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
 	   template.update(deleteSql, param);
    }
}
