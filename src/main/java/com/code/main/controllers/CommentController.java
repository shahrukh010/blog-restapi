package com.code.main.controllers;

import com.code.main.payload.CommentDto;
import com.code.main.payload.CommentResponse;
import com.code.main.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/posts/{id}/comment")
    public ResponseEntity<CommentDto> createComment(@Valid @PathVariable("id") String postid, @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(postid, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postid}/comment")
    public ResponseEntity<CommentResponse> getCommentByPostId(@PathVariable("postid") String postid) {
        return new ResponseEntity<>(commentService.getCommentByPostId(postid), HttpStatus.OK);
    }

    @GetMapping("/posts/{postid}/comment/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postid") String postid, @PathVariable("id") String id) {

        return new ResponseEntity<CommentDto>(commentService.getCommentById(postid, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/posts/{postid}/comment/{id}")
    public ResponseEntity<CommentDto> updateComment(@Valid @PathVariable("postid") String post,
                                                    @PathVariable("id") String id,
                                                    @RequestBody CommentDto commentDto) {

        CommentDto dto = commentService.updateComment(post, id, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/posts/{postid}/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postid") String postid,
                                                @PathVariable("id") String id) {

        commentService.deleteById(postid, id);
        return new ResponseEntity<>("delete comment successfully.", HttpStatus.OK);
    }

}
