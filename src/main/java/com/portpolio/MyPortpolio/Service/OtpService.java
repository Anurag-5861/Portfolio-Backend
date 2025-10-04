package com.portpolio.MyPortpolio.Service;

import org.springframework.stereotype.Service;

@Service
public interface OtpService {
    String generateAndSaveOtp(String email);
    boolean isValid(String email,String enteredOtp);
}
