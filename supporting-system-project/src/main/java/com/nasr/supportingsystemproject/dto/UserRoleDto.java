package com.nasr.supportingsystemproject.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto extends UserDto {

    private RoleDto roleDto;

    @Data
    @AllArgsConstructor
    public static class RoleDto{
        private String name;
    }
}
