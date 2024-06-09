package com.code.main.services;

import com.code.main.payload.CommentDto;
import com.code.main.payload.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentDto createComment(String postid, CommentDto commentDto);

    CommentResponse getCommentByPostId(String postId);

    CommentDto getCommentById(String postid, String id);

    CommentDto updateComment(String postid, String id, CommentDto commentDto);

    void deleteById(String postid, String id);
}
