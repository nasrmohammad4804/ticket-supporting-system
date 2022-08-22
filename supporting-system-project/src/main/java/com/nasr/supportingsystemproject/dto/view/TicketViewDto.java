package com.nasr.supportingsystemproject.dto.view;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TicketViewDto {

    @NotBlank(message = "ticket description is mandatory")
    private String description;

}
