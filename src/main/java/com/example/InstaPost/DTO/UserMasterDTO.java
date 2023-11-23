package com.example.InstaPost.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class UserMasterDTO {
    private Long id;
    private String name;
    private String email;
    private BigDecimal mobileNo;
    private String dateOfBirth;
}
