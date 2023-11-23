package com.example.InstaPost.Controller;

import com.example.InstaPost.DTO.UserMasterDTO;
import com.example.InstaPost.exception.DataNotErrorResponse;
import com.example.InstaPost.exception.DataNotFoundException;
import com.example.InstaPost.modal.UserMaster;
import com.example.InstaPost.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/storeUser")
    public ResponseEntity<UserMasterDTO> storeUser(@RequestBody UserMaster user)
    {
       UserMasterDTO newUser= userService.storeUser(user);
       return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<UserMasterDTO>> getAllUsers()
    {    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMasterDTO> updateUser(@PathVariable Long id , @RequestBody UserMaster user)
    {
        return new ResponseEntity<>(userService.updateuser(id,user), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<DataNotErrorResponse> handleException(DataNotFoundException exe) {
        DataNotErrorResponse response = new DataNotErrorResponse();
        response.setMessage(String.valueOf(HttpStatus.NOT_FOUND.value()));
        response.setMessage(exe.getMessage());
        return ResponseEntity.ok(response);
    }

}
