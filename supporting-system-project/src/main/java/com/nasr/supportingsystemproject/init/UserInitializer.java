package com.nasr.supportingsystemproject.init;

import com.nasr.supportingsystemproject.domain.Customer;
import com.nasr.supportingsystemproject.domain.Manager;
import com.nasr.supportingsystemproject.domain.Support;
import com.nasr.supportingsystemproject.domain.User;
import com.nasr.supportingsystemproject.service.RoleService;
import com.nasr.supportingsystemproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class UserInitializer implements CommandLineRunner  {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Long count = userService.count();
        if (count==0)
            saveDefaultUser();
    }

    private void saveDefaultUser() {


        Customer customer  =Customer.builder().email("nasrmohammad4804@gmail.com")
                .password(passwordEncoder.encode("88888888"))
                .phoneNumber("09031261399")
                .firstName("mohammad")
                .lastName("nasr")
                .role(roleService.getByRoleName("customer"))
                .build();

        Support support  = Support.builder().email("javad@gmail.com")
                .password(passwordEncoder.encode("12345678"))
                .phoneNumber("09018732571")
                .firstName("javad")
                .lastName("zare")
                .role(roleService.getByRoleName("support"))
                .isEnable(true)
                .build();

        Manager manager  = Manager.builder().email("taha@gmail.com")
                .password(passwordEncoder.encode("13804804"))
                .phoneNumber("09900974359")
                .firstName("taha")
                .lastName("ahmadi")
                .role(roleService.getByRoleName("manager"))
                .isEnable(true)
                .build();

        userService.saveAll(List.of(customer,support,manager));
    }

}
