package com.example.Service;

import java.util.List;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    
    public List<Comment> findByArticleId(Integer articleId) {
        List<Comment> commentList=commentRepository.findByArticleId(articleId);
        return commentList;
    }
}
