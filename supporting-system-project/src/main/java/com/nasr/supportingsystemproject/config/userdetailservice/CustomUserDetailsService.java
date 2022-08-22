package com.nasr.supportingsystemproject.config.userdetailservice;

import com.nasr.supportingsystemproject.config.SecurityUser;
import com.nasr.supportingsystemproject.domain.User;
import com.nasr.supportingsystemproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("dont find any user with email : "+email ) );

        return new SecurityUser(user);
    }
}
