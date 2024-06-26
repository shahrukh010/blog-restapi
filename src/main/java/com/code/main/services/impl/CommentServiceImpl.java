package com.code.main.services.impl;

import com.code.main.exception.BlogApiException;
import com.code.main.exception.ResourceNotFoundException;
import com.code.main.models.Comment;
import com.code.main.models.Post;
import com.code.main.payload.CommentDto;
import com.code.main.payload.CommentResponse;
import com.code.main.repository.CommentRepository;
import com.code.main.repository.PostRepository;
import com.code.main.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(String postid, CommentDto commentDto) {

        Post post = postRepository.findById(postid).orElseThrow(() -> new ResourceNotFoundException("post", "id", postid));

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment newcomment = commentRepository.save(comment);
        return mapToDto(newcomment);
    }

    @Override
    public CommentResponse getCommentByPostId(String postId) {

        List<Comment> commentList = commentRepository.findByPostId(postId);
        List<CommentDto> comments = commentList.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(comments);
        return commentResponse;
    }

    @Override
    public CommentDto getCommentById(String postid, String id) {

//        List<Comment> commentList = commentRepository.findByPostId(postid);
//        List<CommentDto> dto = commentList.stream().filter(comment -> comment.getUuid().equals(id)).map(c -> mapToDto(c)).collect(Collectors.toList());
//        return dto.get(0);

        Post post = postRepository.findById(postid).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "uid", id));
        if (!comment.getPost().getId().equals(post.getId())) {

            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Id is not matched");

        }

        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(String postid, String id, CommentDto commentDto) {

        Post post = postRepository.findById(postid).orElseThrow(() -> new ResourceNotFoundException("post", "post", id));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Id is not matched");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment comment1 = commentRepository.save(comment);
        return mapToDto(comment1);
    }

    @Override
    public void deleteById(String postid, String id) {

        Post post = postRepository.findById(postid).orElseThrow(() -> new ResourceNotFoundException("post", "post", postid));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Id not found");
        }
        commentRepository.deleteById(id);
    }


    private Comment mapToEntity(CommentDto commentDto) {

        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }

    private CommentDto mapToDto(Comment comment) {

        CommentDto dto = mapper.map(comment, CommentDto.class);
//        CommentDto dto = new CommentDto();
//        dto.setName(comment.getName());
//        dto.setEmail(comment.getEmail());
//        dto.setBody(comment.getBody());
        return dto;
    }
}
