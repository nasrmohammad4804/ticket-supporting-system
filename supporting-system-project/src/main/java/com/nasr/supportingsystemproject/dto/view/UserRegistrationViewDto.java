package com.nasr.supportingsystemproject.dto.view;

import com.nasr.supportingsystemproject.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationViewDto {

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @Email
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;

    @NotNull
    private UserType userType;
}
