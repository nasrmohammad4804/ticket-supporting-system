package com.nasr.supportingsystemproject.domain;

import com.nasr.supportingsystemproject.base.domain.BaseEntity;
import com.nasr.supportingsystemproject.domain.enumeration.UserType;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.nasr.supportingsystemproject.domain.Support.SUPPORT_ENTITY;

@Setter
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = SUPPORT_ENTITY)
public class Support extends User {
    public static final String SUPPORT_ENTITY = "support";


    @Builder
    public Support(String firstName, String lastName, String email, String password, String phoneNumber, Role role, boolean isEnable, UserType userType) {
        super(firstName, lastName, email, password, phoneNumber, role, isEnable, userType);
    }
}
