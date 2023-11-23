package com.example.InstaPost.Controller;

import com.example.InstaPost.DTO.CommentDto;
import com.example.InstaPost.modal.Comments;
import com.example.InstaPost.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/comment")
public class CommentContoller {
    @Autowired
    private CommentService commentService;

    public CommentContoller(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping()
    public Comments storeComment(@RequestBody CommentDto dto)
    {
        return commentService.StoreComment(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable Long id)
    {
        return new ResponseEntity<>(commentService.getAllCommentByPostId(id), HttpStatus.OK);
    }
    @PutMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId")Long postId,@PathVariable(value = "commentId") Long commentId,@RequestBody Comments comment)
    {
      CommentDto updatedcommment=  commentService.updateComment(postId,commentId,comment);
      return new ResponseEntity<>(updatedcommment,HttpStatus.OK);
    }

}
