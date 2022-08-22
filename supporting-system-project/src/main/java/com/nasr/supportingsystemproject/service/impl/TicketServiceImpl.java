package com.nasr.supportingsystemproject.service.impl;

import com.nasr.supportingsystemproject.base.service.impl.BaseServiceImpl;
import com.nasr.supportingsystemproject.constant.ConstantField;
import com.nasr.supportingsystemproject.domain.Ticket;
import com.nasr.supportingsystemproject.domain.enumeration.TicketStatus;
import com.nasr.supportingsystemproject.dto.TicketDto;
import com.nasr.supportingsystemproject.dto.view.TicketViewDto;
import com.nasr.supportingsystemproject.exception.ModelNotFoundException;
import com.nasr.supportingsystemproject.mapper.TicketMapper;
import com.nasr.supportingsystemproject.repository.TicketRepository;
import com.nasr.supportingsystemproject.service.CustomerService;
import com.nasr.supportingsystemproject.service.TicketService;
import com.nasr.supportingsystemproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl extends BaseServiceImpl<Ticket, Long, String, TicketRepository> implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    @Lazy
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    public TicketServiceImpl(TicketRepository repository) {
        super(repository);
    }

    @Override
    public Class<Ticket> getEntityClass() {
        return Ticket.class;
    }

    @Override
    public List<TicketDto> getTicketByCustomerEmail(String email) {
        return repository.findAllByCustomerEmail(email);
    }

    @Override
    @Transactional
    public TicketDto saveOrUpdate(TicketViewDto ticketViewDto, String customerEmail) {
        Ticket ticket = ticketMapper.convertTicketViewDtoToTicket(ticketViewDto);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCustomer(customerService.getByEmail(customerEmail));

        return ticketMapper.convertTicketToTicketDto(super.saveOrUpdate(ticket));
    }

    @Override
    public List<TicketDto> getTickets() {
        return ticketMapper.convertTicketsToTicketDtos(repository.findAll());
    }

    @Override
    @Transactional
    public TicketDto saveAnswer(Ticket ticket) {
        Ticket ticketEntity = repository.findById(ticket.getId())
                .orElseThrow(() -> new ModelNotFoundException("dont find any ticket with id : " + ticket.getId()));

        ticketEntity.setAnswer(ticket.getAnswer());
        ticket.setStatus(TicketStatus.CLOSED);
        return ticketMapper.convertTicketToTicketDto(ticket);
    }

    @Override
    @Transactional
    public TicketDto updateStatus(Long id, TicketStatus status) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("dont find any ticket with id : " + id));

        if (ticket.getStatus().equals(TicketStatus.CLOSED))
            throw new AccessDeniedException("");


        if (status.equals(TicketStatus.CLOSED)) {
            Authentication authentication = SecurityContextHolder.getContext()
                    .getAuthentication();

            String userType = userService.getUserType(authentication.getName());
            if (userType.equalsIgnoreCase(ConstantField.MANAGER))
                ticket.setStatus(status);
            
        } else ticket.setStatus(status);


        return ticketMapper.convertTicketToTicketDto(ticket);
    }

    @Override

    public void deleteCustomerTickets(Long customerId) {
        repository.deleteCustomerTickets(customerId);
    }

}
