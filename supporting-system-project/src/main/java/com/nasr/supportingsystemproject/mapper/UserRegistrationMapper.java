package com.nasr.supportingsystemproject.mapper;

import com.nasr.supportingsystemproject.domain.User;
import com.nasr.supportingsystemproject.dto.UserDto;
import com.nasr.supportingsystemproject.dto.view.UserRegistrationViewDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserRegistrationMapper  {

    UserDto convertEntityToDto(User user);
    User convertDtoToEntity(UserRegistrationViewDto dto);
}
