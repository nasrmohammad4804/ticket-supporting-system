package com.nasr.supportingsystemproject.controller;

import com.nasr.supportingsystemproject.domain.enumeration.UpdateCustomerRole;
import com.nasr.supportingsystemproject.dto.CustomerDto;
import com.nasr.supportingsystemproject.dto.UserRoleDto;
import com.nasr.supportingsystemproject.exception.ResponseTemplate;
import com.nasr.supportingsystemproject.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/customer")
@SecurityRequirement(name = "Bearer Authentication")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * we call this api in manager panel for get customer information
     *
     * @return List<CustomerDto> as all customer exists in system </>
     */
    @GetMapping("/all")
    public ResponseEntity<?> getCustomers() {
        List<CustomerDto> dtos = customerService.getAll();
        return ResponseEntity.ok(
                new ResponseTemplate<>(true, dtos, "ok")
        );
    }

    /**
     * this api accessible by manager and can change customer role to (manager - support)
     * @param email as customerEmail
     * @param updateCustomerRole as status of new role
     * @return userRoleDto for get new information
     */
    @PutMapping("/change-role/{email}")
    public ResponseEntity<ResponseTemplate<?>> changeCustomerRole(  @PathVariable @NotBlank String email,
                                                                    @RequestParam UpdateCustomerRole updateCustomerRole) {
        UserRoleDto userRoleDto = customerService.updateCustomerRole(email, updateCustomerRole);
        return ResponseEntity.ok(
                new ResponseTemplate<>(true, userRoleDto, "changed successfully customer role to : " + updateCustomerRole)
        );

    }
}
