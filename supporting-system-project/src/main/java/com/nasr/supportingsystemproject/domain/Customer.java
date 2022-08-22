package com.nasr.supportingsystemproject.domain;

import com.nasr.supportingsystemproject.domain.enumeration.UserType;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = Customer.CUSTOMER_ENTITY)
public class Customer extends User{

    public static final String CUSTOMER_ENTITY = "customer";

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Ticket> tickets =  new ArrayList<>();

    @Builder
    public Customer(String firstName, String lastName, String email, String password, String phoneNumber, Role role, boolean isEnable, UserType userType) {
        super(firstName, lastName, email, password, phoneNumber, role, isEnable, userType);
    }

}
