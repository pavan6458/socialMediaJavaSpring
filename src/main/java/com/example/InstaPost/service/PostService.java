package com.example.InstaPost.service;

import com.example.InstaPost.DTO.PostDto;
import com.example.InstaPost.modal.Post;

import java.util.List;

public interface PostService {

    public Post storepost(Long userId,Post post);
    public List<PostDto> getAllPost();
    public List<PostDto> getAllPostByUserId(Long userId);

    public Post getPostById(Long id);

    public Post updatePostById(Long id , Post post);

}
