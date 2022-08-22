package com.nasr.supportingsystemproject.dto.view;

import com.nasr.supportingsystemproject.domain.enumeration.UpdateCustomerRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UpdateCustomerRoleViewDto implements Serializable {

    @NotBlank(message = "customer email is mandatory")
    private String email;

    @NotBlank(message = "status is mandatory")
    private UpdateCustomerRole roleStatus;
}
