package com.example.InstaPost.Repository;

import com.example.InstaPost.modal.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserMaster,Long> {
}
