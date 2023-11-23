package com.example.InstaPost.service;

import com.example.InstaPost.DTO.CommentDto;
import com.example.InstaPost.modal.Comments;

import java.util.List;


public interface CommentService {
    public Comments StoreComment(CommentDto dto);
    public List<CommentDto> getAllCommentByPostId(Long id);

    public CommentDto updateComment(Long postId,Long commentId,Comments comments);
}
