package com.code.main.services;

import com.code.main.payload.PostDto;
import org.springframework.stereotype.Service;

public interface PostService {

    public PostDto createPost(PostDto postDto);
}

