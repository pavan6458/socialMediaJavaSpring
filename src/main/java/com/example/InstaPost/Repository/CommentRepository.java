package com.example.InstaPost.Repository;

import com.example.InstaPost.modal.Comments;
import com.example.InstaPost.modal.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Long>{
    public List<Comments> findByPostId(Long id);
}
