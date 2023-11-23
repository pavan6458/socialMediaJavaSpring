package com.example.InstaPost.service.impp;

import com.example.InstaPost.DTO.PostDto;
import com.example.InstaPost.modal.UserMaster;
import com.example.InstaPost.service.PostService;
import com.example.InstaPost.modal.Post;
import com.example.InstaPost.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class postServiceImpp implements PostService {

    private postRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public postServiceImpp(com.example.InstaPost.Repository.postRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post storepost(Long userId, Post post) {
        UserMaster fetchedUser = userRepository.findById(userId).orElseThrow();
        post.setUser(fetchedUser);
        return postRepository.save(post);
    }

    @Override
    public List<PostDto> getAllPost() {


        return postRepository.findAll().stream().map((items) -> postToPostDto(items)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        log.info(posts.toString());
        return posts.stream().map((items) -> postToPostDto(items)).collect(Collectors.toList());
    }


    @Override
    public Post getPostById(Long id) {

        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public Post updatePostById(Long id, Post post) {
        Post fetchedPost = postRepository.findById(id).orElseThrow();
        fetchedPost.setLocation(post.getLocation());
        fetchedPost.setTitle(post.getTitle());
        fetchedPost.setDescription(post.getDescription());

        Post updatedPost = postRepository.save(fetchedPost);
        return updatedPost;

    }

    public PostDto postToPostDto(Post post) {
        PostDto postdto = new PostDto();
        postdto.setUserId(post.getUser().getId());
        postdto.setDescription(post.getDescription());
        postdto.setLocation(post.getLocation());
        postdto.setTitle(post.getTitle());
        postdto.setDateCreated(post.getDateCreated());
        postdto.setUpdatedDate(post.getUpdatedDate());
        return postdto;

    }
}
