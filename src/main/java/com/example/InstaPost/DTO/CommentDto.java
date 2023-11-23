package com.example.InstaPost.DTO;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String email;
    private String body;
    private String content;
    private Long postId;
}
