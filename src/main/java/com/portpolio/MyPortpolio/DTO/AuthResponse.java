package com.portpolio.MyPortpolio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessJWT;
    private String refreshToken;
    private boolean success=false;
}
