package com.nasr.supportingsystemproject.domain;

import com.nasr.supportingsystemproject.domain.enumeration.UserType;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.nasr.supportingsystemproject.domain.Manager.MANAGER_ENTITY;

@Setter
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = MANAGER_ENTITY)

public class Manager extends User{
    public static final String MANAGER_ENTITY = "manager";

    @Builder
    public Manager(String firstName, String lastName, String email, String password, String phoneNumber, Role role, boolean isEnable, UserType userType) {
        super(firstName, lastName, email, password, phoneNumber, role, isEnable, userType);
    }

}
