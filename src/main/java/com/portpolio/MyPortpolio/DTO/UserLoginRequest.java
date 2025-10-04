package com.portpolio.MyPortpolio.DTO;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
