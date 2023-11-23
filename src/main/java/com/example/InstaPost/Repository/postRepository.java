package com.example.InstaPost.Repository;

import com.example.InstaPost.modal.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface postRepository extends JpaRepository<Post,Long> {
    public List<Post> findByUserId(Long userId);
}
