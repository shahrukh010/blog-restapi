package com.code.main.services.impl;

import com.code.main.models.Post;
import com.code.main.payload.PostDto;
import com.code.main.repository.PostRepository;
import com.code.main.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert dto to Post;
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        Post newpost = postRepository.save(post);

        //convert post to dto;
        PostDto dto = new PostDto();
        dto.setTitle(newpost.getTitle());
        dto.setDescription(newpost.getDescription());
        dto.setContent(newpost.getContent());
        return dto;

    }
}
