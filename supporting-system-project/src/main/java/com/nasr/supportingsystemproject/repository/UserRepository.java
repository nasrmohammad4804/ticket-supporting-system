package com.nasr.supportingsystemproject.repository;

import com.nasr.supportingsystemproject.domain.User;
import com.nasr.supportingsystemproject.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query("select new com.nasr.supportingsystemproject.dto.UserDto(u.id,u.firstName,u.lastName,u.email,u.phoneNumber) " +
            "from User as u where u.email = :email")
    Optional<UserDto> findUserProfileByEmail(String email);

    @Query(value = "select u.user_type from user_table as u where u.email= :email ",nativeQuery = true)
    String findUserTypeByEmail(String email);
}
