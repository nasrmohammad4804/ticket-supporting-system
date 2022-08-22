package com.nasr.supportingsystemproject.dto;

import com.nasr.supportingsystemproject.domain.enumeration.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Long id;

    private String description;

    private String answer;

    private TicketStatus status;

    protected String createdBy;

    protected LocalDateTime createdDate;

    protected String lastModifiedBy;

    protected LocalDateTime lastModifiedDate;
}
