package com.nasr.supportingsystemproject.domain.enumeration;

import com.nasr.supportingsystemproject.constant.ConstantField;
import com.nasr.supportingsystemproject.domain.Customer;
import com.nasr.supportingsystemproject.domain.Manager;
import com.nasr.supportingsystemproject.domain.Support;
import com.nasr.supportingsystemproject.domain.User;
import com.nasr.supportingsystemproject.service.RoleService;
import com.nasr.supportingsystemproject.service.impl.RoleServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public enum UserType {


    MANAGER() {
        @Override
        public User getUser(User user) {
            return Manager.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .firstName(user.getFirstName())
                    .isEnable(false)
                    .lastName(user.getLastName())
                    .phoneNumber(user.getPhoneNumber())
                    .role(roleService.getByRoleName(ConstantField.MANAGER)).build();
        }
    },
    SUPPORT() {
        @Override
        public User getUser(User user) {
            return Support.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .firstName(user.getFirstName())
                    .isEnable(false)
                    .lastName(user.getLastName())
                    .phoneNumber(user.getPhoneNumber())
                    .role(roleService.getByRoleName(ConstantField.SUPPORT)).build();
        }
    },
    CUSTOMER(){
        @Override
        public User getUser(User user) {
            return Customer.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .firstName(user.getFirstName())
                    .isEnable(false)
                    .lastName(user.getLastName())
                    .phoneNumber(user.getPhoneNumber())
                    .role(roleService.getByRoleName(ConstantField.CUSTOMER)).build();
        }
    };

    protected RoleService roleService;

    public void setRoleService(RoleService roleService){
        this.roleService = roleService;
    }


    public abstract User getUser(User user);


}
