package com.nasr.supportingsystemproject.mapper;

import com.nasr.supportingsystemproject.domain.Customer;
import com.nasr.supportingsystemproject.dto.CustomerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerDto convertEntityToDto(Customer customer);
    List<CustomerDto> convertEntitiesToDtos(List<Customer> customers);
}
