package com.nasr.supportingsystemproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;

}
