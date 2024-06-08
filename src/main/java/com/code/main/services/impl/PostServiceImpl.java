package com.code.main.services.impl;

import com.code.main.exception.ResourceNotFound;
import com.code.main.models.Post;
import com.code.main.payload.PostDto;
import com.code.main.repository.PostRepository;
import com.code.main.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);
        Post newpost = postRepository.save(post);
        PostDto dto = mapToDto(newpost);
        return dto;

    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getById(String id) {


        Post post = postRepository.findById(id).get();
        return mapToDto(post);
    }

    @Override
    public PostDto updateById(PostDto postDto, String id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post updatepost = postRepository.save(post);
        return mapToDto(updatepost);
    }

    @Override
    public void deleteById(String id) {

        postRepository.deleteById(id);
    }


    //convert entity to dto
    private PostDto mapToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setUuid(post.getId());
        return dto;
    }

    //convert dto to entity;
    private Post mapToEntity(PostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());
        return post;
    }
}
