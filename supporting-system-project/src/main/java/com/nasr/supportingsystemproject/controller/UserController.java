package com.nasr.supportingsystemproject.controller;

import com.nasr.supportingsystemproject.domain.User;
import com.nasr.supportingsystemproject.dto.UserDto;
import com.nasr.supportingsystemproject.dto.view.UserRegistrationViewDto;
import com.nasr.supportingsystemproject.exception.ResponseTemplate;
import com.nasr.supportingsystemproject.mapper.UserRegistrationMapper;
import com.nasr.supportingsystemproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRegistrationMapper userRegistrationMapper;

    @Autowired
    private UserService userService;

    /**
     * this api is permit for every user can register to application but manager need to activate this for user able to login
     * @param dto as information for register user
     * @return data
     */
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserRegistrationViewDto dto) {
        User user = userRegistrationMapper.convertDtoToEntity(dto);
        User userEntity = userService.saveOrUpdate(user);

        return ResponseEntity.ok(
                new ResponseTemplate<>(true, userRegistrationMapper.convertEntityToDto(userEntity), "ok"));
    }

    /**
     * this api for get profile for each user type such (customer - manager - support)
     * @param username as email
     * @return userInfo
     */
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "get user profile (regardless user is manager or supporter or customer)", responses = {
            @ApiResponse(responseCode = "200 ", description = "if user successfully get profile"),
            @ApiResponse(responseCode = "404", description = "if dont find any user with given username"),
            @ApiResponse(responseCode = "403", description = "if token not valid and or finished expiration time")
    })
    @GetMapping("/profile/{username}")
    public ResponseEntity<ResponseTemplate<UserDto>> getUserProfile(@PathVariable String username) {
        UserDto userDto = userService.getUserProfileByEmail(username);
        return ResponseEntity.ok(
                new ResponseTemplate<>(true, userDto, "ok")
        );
    }


}
