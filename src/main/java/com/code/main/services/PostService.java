package com.code.main.services;

import com.code.main.payload.PostDto;
import com.code.main.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    public PostResponse getAllPost(int pageno, int pagesize, String sortBy, String sorDir);

    public PostDto getById(String id);

    public PostDto updateById(PostDto postDto, String id);

    public void deleteById(String id);
}

