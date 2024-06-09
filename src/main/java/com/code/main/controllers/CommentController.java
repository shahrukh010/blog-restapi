package com.code.main.controllers;

import com.code.main.payload.CommentDto;
import com.code.main.payload.CommentResponse;
import com.code.main.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{id}/comment")
    public ResponseEntity<CommentDto> createComment(@PathVariable("id") String postid, @RequestBody CommentDto commentDto) {

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

    @PutMapping("/posts/{postid}/comment/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postid") String post,
                                                    @PathVariable("id") String id,
                                                    @RequestBody CommentDto commentDto) {

        CommentDto dto = commentService.updateComment(post, id, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postid}/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postid") String postid,
                                                @PathVariable("id") String id) {

        commentService.deleteById(postid, id);
        return new ResponseEntity<>("delete comment successfully.", HttpStatus.OK);
    }

}
