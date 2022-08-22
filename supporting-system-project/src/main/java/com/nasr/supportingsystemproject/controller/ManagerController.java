package com.nasr.supportingsystemproject.controller;

import com.nasr.supportingsystemproject.exception.ResponseTemplate;
import com.nasr.supportingsystemproject.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserService userService;

    /**
     *  this api for activate user registered in application and accessible by manager for activating user
     * @param userId as userIdentifier
     * @return void
     */
    @PutMapping("/activate-user/{userId}")
    public ResponseEntity<?> activateUserRegistered(@PathVariable Long userId) {
        userService.activateUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseTemplate<>(true, null, "activated user successfully"));

    }
}
