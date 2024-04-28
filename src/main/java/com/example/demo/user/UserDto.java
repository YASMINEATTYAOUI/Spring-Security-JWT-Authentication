package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  UserDto {

    private Long id;
    private Date creationDate;
    private Date lastModifiedDate;

    private  String userName;
    private String firstName;
    private String lastName;
    private Integer phoneNumber;
    private String email;
    private String password;
    private boolean mfaEnabled;
    private String secret;
}
