package com.portpolio.MyPortpolio.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UsersSignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> roles;
}
