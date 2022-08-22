package com.nasr.supportingsystemproject.init;

import com.nasr.supportingsystemproject.constant.ConstantField;
import com.nasr.supportingsystemproject.domain.Role;
import com.nasr.supportingsystemproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.nasr.supportingsystemproject.constant.ConstantField.*;

@Component
@Order(1)
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Long count = roleService.count();
        if (count==0)
            saveDefaultRole();
    }

    private void saveDefaultRole() {
        List<Role> roles = List.of(
                Role.builder().name(CUSTOMER).build(),
                Role.builder().name(SUPPORT).build(),
                Role.builder().name(MANAGER).build()
        );

        roleService.saveAll(roles);
    }

}
