package com.code.main.controllers;

import com.code.main.exception.ResourceNotFound;
import com.code.main.payload.PostDto;
import com.code.main.payload.PostResponse;
import com.code.main.services.PostService;
import com.code.main.utils.Utils;
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
    public PostResponse getAllPost(@RequestParam(value = "pagno", defaultValue = Utils.DEFAULT_PAGENO, required = false) int pageno,
                                   @RequestParam(value = "pagesize", defaultValue = Utils.DEFAULT_PAGESIZE, required = false) int pagesize,
                                   @RequestParam(value = "sortBy", defaultValue = Utils.DEFAULT_SORTBY, required = false) String sortBy,
                                   @RequestParam(value = "sortDir", defaultValue = Utils.DEFAULT_SORTDIR, required = false) String sortDir) {

        return postService.getAllPost(pageno, pagesize, sortBy, sortDir);
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
