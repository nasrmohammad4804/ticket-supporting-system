package com.nasr.supportingsystemproject.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDto extends UserDto implements Serializable {

    public CustomerDto(Long id, String firstName, String lastName, String email, String phoneNumber) {
        super(id, firstName, lastName, email, phoneNumber);
    }
}
