package com.example.InstaPost.service.impp;

import com.example.InstaPost.DTO.CommentDto;
import com.example.InstaPost.Repository.CommentRepository;
import com.example.InstaPost.Repository.postRepository;
import com.example.InstaPost.modal.Comments;
import com.example.InstaPost.modal.Post;
import com.example.InstaPost.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentServiceImpp implements CommentService {
    @Autowired
    private postRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public CommentServiceImpp(com.example.InstaPost.Repository.postRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comments StoreComment(CommentDto dto) {
        Comments comment = ConverttoComment(dto);

        Post post =  postRepository.findById(dto.getPostId()).orElseThrow();
//        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getAllCommentByPostId(Long id) {
        log.info(commentRepository.findByPostId(id).toString());

        return commentRepository.findByPostId(id).stream().map((list)->ConvertCommentToCommentDTo(list,id)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, Comments comments) {
        Post post = postRepository.findById(postId).orElseThrow();
        Comments comment = commentRepository.findById(commentId).orElseThrow();

//        if (!comment.getPost().getId().equals(post.getId()))
//        {
//            throw new RuntimeException("hello");
//        }
        comment.setEmail(comments.getEmail());
        comment.setBody(comments.getBody());
        comment.setContent(comments.getContent());

        Comments updatedComment = commentRepository.save(comment);

        return ConvertCommentToCommentDTo(updatedComment,postId);
    }

    public Comments ConverttoComment(CommentDto dto)
    {
        Comments comment = new Comments();
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setContent(dto.getContent());
      return comment;
    }

    public CommentDto ConvertCommentToCommentDTo(Comments comment,Long Postid)
    {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setContent(comment.getContent());
        commentDto.setEmail(comment.getEmail());
        commentDto.setPostId(Postid);
        return commentDto;

    }

}
