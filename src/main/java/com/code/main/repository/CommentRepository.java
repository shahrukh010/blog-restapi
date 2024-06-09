package com.code.main.repository;

import com.code.main.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    public List<Comment> findByPostId(String postId);
}
