package com.nasr.supportingsystemproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ResponseTemplate<String>> modelNotFoundException(ModelNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseTemplate<>(false, null, e.getMessage()));
    }
    //note if we dont call api and occur error in filter then exception controller advice dont catch exception
    // if you call api and in service occurred exception in this situation exceptionController called
    //but if error occurred in filter you need to manually catch exception and send error code and message to client

    /*@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseTemplate<String>> usernameNotFoundException(UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseTemplate<>(false,null,e.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseTemplate<String>> invalidPasswordException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseTemplate<>(false,null,e.getMessage()));
    }*/
}
