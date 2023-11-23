package com.example.InstaPost.service;

import com.example.InstaPost.DTO.UserMasterDTO;
import com.example.InstaPost.modal.UserMaster;

import java.util.List;

public interface UserService {
    public UserMasterDTO storeUser(UserMaster newUser);

    public List<UserMasterDTO> getAllUsers();

    public UserMasterDTO updateuser(Long id, UserMaster updateUserData);

    public void deleteUser(Long id);

}


