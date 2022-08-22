package com.nasr.supportingsystemproject.service.impl;

import com.nasr.supportingsystemproject.base.service.impl.BaseServiceImpl;
import com.nasr.supportingsystemproject.domain.Customer;
import com.nasr.supportingsystemproject.domain.Role;
import com.nasr.supportingsystemproject.domain.enumeration.UpdateCustomerRole;
import com.nasr.supportingsystemproject.dto.CustomerDto;
import com.nasr.supportingsystemproject.dto.UserRoleDto;
import com.nasr.supportingsystemproject.dto.view.UpdateCustomerRoleViewDto;
import com.nasr.supportingsystemproject.exception.ModelNotFoundException;
import com.nasr.supportingsystemproject.mapper.UserRoleMapper;
import com.nasr.supportingsystemproject.repository.CustomerRepository;
import com.nasr.supportingsystemproject.service.CustomerService;
import com.nasr.supportingsystemproject.service.RoleService;
import com.nasr.supportingsystemproject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, String, CustomerRepository>
        implements CustomerService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public Customer getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ModelNotFoundException("dont find any customer with email : " + email));
    }

    @Override
    public List<CustomerDto> getAll() {
        return repository.findCustomers();
    }

    /**
     * we need to change user role addition we need to update userType
     * and also remove customerId for all ticket saved by this customer
     * and latest observer ticket in support or manager panel dont be able to recognize owner of ticket
     * because anymore don't have customer and changed user access
     *
     * @param email, role  for get status and customerId
     * @return userRoleDto
     */
    @Override
    @Transactional
    public UserRoleDto updateCustomerRole(String email ,UpdateCustomerRole updateCustomerRole) {
        Customer customer = repository.findByEmail(email)
                .orElseThrow(() -> new ModelNotFoundException("dont find any customer with email : " +email));

        Role role = updateCustomerRole.getRole(roleService);
        customer.setRole(role);
        repository.updateUserType(role.getName(),email);
        ticketService.deleteCustomerTickets(customer.getId());
        return userRoleMapper.convertEntityToDto(customer);
    }
}
