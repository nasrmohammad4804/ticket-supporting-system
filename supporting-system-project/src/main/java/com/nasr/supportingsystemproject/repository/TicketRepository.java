package com.nasr.supportingsystemproject.repository;

import com.nasr.supportingsystemproject.domain.Ticket;
import com.nasr.supportingsystemproject.dto.TicketDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select new  com.nasr.supportingsystemproject.dto.TicketDto(t.id,t.description,t.answer,t.status,t.createdBy,t.createdDate,t.lastModifiedBy,t.lastModifiedDate) " +
            "from Ticket as t join t.customer as c where c.email= :email")
    List<TicketDto> findAllByCustomerEmail(String email);

    @Modifying
    @Query("update Ticket as t set t.customer = null where t.customer.id = :customerId")
    void deleteCustomerTickets(Long customerId);
}
