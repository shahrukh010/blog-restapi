package com.code.main.services.impl;

import com.code.main.exception.ResourceNotFound;
import com.code.main.models.Post;
import com.code.main.payload.PostDto;
import com.code.main.payload.PostResponse;
import com.code.main.repository.PostRepository;
import com.code.main.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPost(int pageno, int pagesize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageno, pagesize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();
        List<PostDto> content = postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageno(String.valueOf(posts.getNumber()));
        postResponse.setPagesize(String.valueOf(posts.getSize()));
        postResponse.setTotalElements(String.valueOf(posts.getTotalElements()));
        postResponse.setTotalPages(String.valueOf(posts.getTotalPages()));
        postResponse.setLast(posts.isLast());
        return postResponse;
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
