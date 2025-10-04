package com.portpolio.MyPortpolio.Exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error->
                    error.getField() + " : " + error.getDefaultMessage()
                )
                .toList();


        return ResponseEntity.badRequest().body(Map.of("status","error","error",errors));
    }

    public ResponseEntity<String> usernameNotFound(UsernameNotFoundException ex){
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

}
