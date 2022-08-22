package com.nasr.supportingsystemproject.service;

import com.nasr.supportingsystemproject.base.service.BaseService;
import com.nasr.supportingsystemproject.domain.Customer;
import com.nasr.supportingsystemproject.domain.enumeration.UpdateCustomerRole;
import com.nasr.supportingsystemproject.dto.CustomerDto;
import com.nasr.supportingsystemproject.dto.UserRoleDto;
import com.nasr.supportingsystemproject.dto.view.UpdateCustomerRoleViewDto;

import java.util.List;

public interface CustomerService extends BaseService<Customer,String,Long> {

    Customer getByEmail(String email);

    List<CustomerDto> getAll();

    UserRoleDto updateCustomerRole(String email, UpdateCustomerRole dto);
}
