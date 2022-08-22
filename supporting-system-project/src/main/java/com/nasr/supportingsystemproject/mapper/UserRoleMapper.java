package com.nasr.supportingsystemproject.mapper;

import com.nasr.supportingsystemproject.domain.Customer;
import com.nasr.supportingsystemproject.dto.UserRoleDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserRoleMapper {

    UserRoleDto convertEntityToDto(Customer customer);

}
