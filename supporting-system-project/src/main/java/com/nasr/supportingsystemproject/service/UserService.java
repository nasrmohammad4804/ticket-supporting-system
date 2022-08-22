package com.nasr.supportingsystemproject.service;

import com.nasr.supportingsystemproject.base.service.BaseService;
import com.nasr.supportingsystemproject.domain.User;
import com.nasr.supportingsystemproject.dto.UserDto;

import java.util.Optional;

public interface UserService extends BaseService<User,String,Long> {
    Long count();

    void saveAll(Iterable<User> users);

    Optional<User> getUserByEmail(String email);

    UserDto getUserProfileByEmail(String email);


    String getUserType(String name);

    void activateUser(Long userId);
}
