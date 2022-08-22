package com.nasr.supportingsystemproject.domain;


import com.nasr.supportingsystemproject.base.domain.BaseEntity;
import com.nasr.supportingsystemproject.domain.enumeration.UserType;
import lombok.*;

import javax.persistence.*;

import static com.nasr.supportingsystemproject.domain.User.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Inheritance
@DiscriminatorColumn(name = USER_TYPE)
@Table(name = TABLE_NAME,uniqueConstraints = {@UniqueConstraint(name = "define_unique",columnNames = {PHONE_NUMBER,EMAIL})})
@AllArgsConstructor
public  class User extends BaseEntity<Long , String> {

    public static final String USER_TYPE ="user_type";
    public static final String TABLE_NAME="user_table";
    public static final String PHONE_NUMBER =  "phone_number";
    public static final String EMAIL =  "email";
    public static final String ROLE_ID= "role_id";

    protected String firstName;

    protected String lastName;

    @Column(name = EMAIL,nullable = false)
    protected String email;

    @Column(nullable = false)
    protected String password;

    @Column(name = PHONE_NUMBER,nullable = false)
    protected String phoneNumber;

    @ManyToOne
    @JoinColumn(name = ROLE_ID)
    protected Role role;

    protected boolean isEnable;

    @Transient
    protected UserType userType;


    public User(String email, String password, String phoneNumber,Role role) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role =  role;
    }


    public User(String firstName, String lastName, String email, String password, String phoneNumber) {
        this(email,password,phoneNumber,null);
        this.firstName = firstName;
        this.lastName = lastName;

    }


}
