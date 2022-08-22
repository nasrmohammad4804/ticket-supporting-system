package com.nasr.supportingsystemproject.mapper;

import com.nasr.supportingsystemproject.domain.Ticket;
import com.nasr.supportingsystemproject.dto.view.TicketAnswerViewDto;
import org.mapstruct.Mapper;

@Mapper
public interface TicketAnswerMapper {

    Ticket convertDtoToEntity(TicketAnswerViewDto dto);
}
