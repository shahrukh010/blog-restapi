package com.code.main.services;

import com.code.main.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    public List<PostDto> getAllPost(int pageno, int pagesize);

    public PostDto getById(String id);

    public PostDto updateById(PostDto postDto, String id);

    public void deleteById(String id);
}

