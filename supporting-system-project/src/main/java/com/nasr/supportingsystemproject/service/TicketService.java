package com.nasr.supportingsystemproject.service;

import com.nasr.supportingsystemproject.base.service.BaseService;
import com.nasr.supportingsystemproject.domain.Ticket;
import com.nasr.supportingsystemproject.domain.enumeration.TicketStatus;
import com.nasr.supportingsystemproject.dto.TicketDto;
import com.nasr.supportingsystemproject.dto.view.TicketViewDto;

import java.util.List;

public interface TicketService extends BaseService<Ticket,String,Long>  {

    List<TicketDto> getTicketByCustomerEmail(String email);

    TicketDto saveOrUpdate(TicketViewDto ticketViewDto, String customerEmail);

    List<TicketDto> getTickets();

    TicketDto saveAnswer(Ticket convertDtoToEntity);

    TicketDto updateStatus(Long id, TicketStatus status);

    void deleteCustomerTickets(Long customerId);
}
