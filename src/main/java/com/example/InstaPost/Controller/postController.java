package com.example.InstaPost.Controller;

import com.example.InstaPost.DTO.PostDto;
import com.example.InstaPost.modal.Post;
import com.example.InstaPost.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class postController {
    @Autowired
    private PostService PostService;

    public postController(com.example.InstaPost.service.PostService postService) {
        PostService = postService;
    }
    @PostMapping("/{Id}")
    public ResponseEntity<Post> storePost(@PathVariable(value = "Id")Long id, @RequestBody Post post)

    {

        return new ResponseEntity<>(PostService.storepost(id,post), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost(){
        return new ResponseEntity<>(PostService.getAllPost(),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable(name = "userId") Long id)
    {
        return new ResponseEntity<>(PostService.getAllPostByUserId(id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id)
    {
        return new ResponseEntity<>(PostService.getPostById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable Long id , @RequestBody Post post)
    {
        Post updatedPost = PostService.updatePostById(id,post);
//        Hibernate.initialize(post.getComment());
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }


}
