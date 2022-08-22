package com.nasr.supportingsystemproject.mapper;

import com.nasr.supportingsystemproject.domain.Ticket;
import com.nasr.supportingsystemproject.dto.TicketDto;
import com.nasr.supportingsystemproject.dto.view.TicketViewDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TicketMapper {

    TicketDto convertTicketToTicketDto(Ticket ticket);

    Ticket convertTicketViewDtoToTicket(TicketViewDto ticketViewDto);

    List<TicketDto> convertTicketsToTicketDtos(List<Ticket> tickets);

}
