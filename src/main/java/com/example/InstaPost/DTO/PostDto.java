package com.example.InstaPost.DTO;



import lombok.Data;

import java.util.Date;
@Data
public class PostDto {
    private String title;
    private String description;
    private String location;
    private Date dateCreated;
    private Date updatedDate;
    private Long userId;
}
