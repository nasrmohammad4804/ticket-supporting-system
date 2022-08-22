package com.nasr.supportingsystemproject.dto.view;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TicketAnswerViewDto {

    @NotNull(message = "ticket id is mandatory for answer to ticket")
    private Long id;

    @NotBlank(message = "answer message is mandatory")
    private String answer;
}
