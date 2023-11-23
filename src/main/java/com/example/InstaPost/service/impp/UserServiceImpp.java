package com.example.InstaPost.service.impp;

import com.example.InstaPost.DTO.UserMasterDTO;
import com.example.InstaPost.Repository.UserRepository;
import com.example.InstaPost.Repository.postRepository;
import com.example.InstaPost.exception.DataNotFoundException;
import com.example.InstaPost.modal.Post;
import com.example.InstaPost.modal.UserMaster;
import com.example.InstaPost.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpp implements UserService {
    private UserRepository userRepository;
    private postRepository postRepository;

    @Autowired
    public UserServiceImpp(UserRepository userRepository, com.example.InstaPost.Repository.postRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public UserMasterDTO storeUser(UserMaster newUser) {

        return convertUserMasterToUserMasterDTO(userRepository.save(newUser));
    }

    @Override
    public List<UserMasterDTO> getAllUsers() {
        log.info(userRepository.findAll().toString());
        return userRepository.findAll().stream().map((item) -> convertUserMasterToUserMasterDTO(item)).collect(Collectors.toList());
    }

    @Override
    public UserMasterDTO updateuser(Long id, UserMaster updateUserData) {
        UserMaster fetchedUser = userRepository.findById(id).orElseThrow();
        fetchedUser.setName(updateUserData.getName());
        fetchedUser.setEmail(updateUserData.getEmail());
        fetchedUser.setMobileNo(updateUserData.getMobileNo());
        return convertUserMasterToUserMasterDTO(userRepository.save(fetchedUser));
    }

    @Override
    public void deleteUser(Long id) {
        UserMaster fetchedUser = userRepository.findById(id).orElseThrow(()->new DataNotFoundException("User Not found with id ->"+ id));
        List<Post> post= postRepository.findByUserId(id);
        postRepository.deleteAll(post);
        log.info(post.toString());
        userRepository.deleteById(id);
    }

    public UserMasterDTO convertUserMasterToUserMasterDTO(UserMaster user) {
        UserMasterDTO userMasterDTO = new UserMasterDTO();
        userMasterDTO.setEmail(user.getEmail());
        userMasterDTO.setName(user.getName());
        userMasterDTO.setDateOfBirth(user.getDateOfBirth());
        userMasterDTO.setMobileNo(user.getMobileNo());
        userMasterDTO.setId(user.getId());

        return userMasterDTO;

    }
}


