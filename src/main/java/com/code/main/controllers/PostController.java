package com.code.main.controllers;

import com.code.main.exception.ResourceNotFound;
import com.code.main.payload.PostDto;
import com.code.main.services.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("create-post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto) {
        return new ResponseEntity<>(postService.createPost(dto), HttpStatus.CREATED);
    }

    @GetMapping("all-posts")
    public List<PostDto> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") String id) {

            return new ResponseEntity<PostDto>(postService.getById(id), HttpStatus.OK);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<PostDto> updatePosts(@RequestBody PostDto postDto, @PathVariable("uuid") String id) {

        return new ResponseEntity<>(postService.updateById(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<String> deletePost(@PathVariable("uuid") String id) {

        postService.deleteById(id);
        return new ResponseEntity<String>("post delete successfully", HttpStatus.OK);
    }

}
