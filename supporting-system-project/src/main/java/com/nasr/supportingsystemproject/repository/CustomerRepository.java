package com.nasr.supportingsystemproject.repository;

import com.nasr.supportingsystemproject.domain.Customer;
import com.nasr.supportingsystemproject.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    @Query("""
            select new com.nasr.supportingsystemproject.dto.CustomerDto(c.id,c.firstName,c.lastName,c.email,c.phoneNumber) from Customer as c
            """)
    List<CustomerDto> findCustomers();

    @Query(value = "update user_table  set user_type = :userType  where email= :email",nativeQuery = true)
    @Modifying
    void updateUserType(String userType,String email);
}
