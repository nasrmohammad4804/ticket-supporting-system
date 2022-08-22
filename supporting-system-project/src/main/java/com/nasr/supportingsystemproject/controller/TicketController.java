package com.nasr.supportingsystemproject.controller;

import com.nasr.supportingsystemproject.domain.enumeration.TicketStatus;
import com.nasr.supportingsystemproject.dto.TicketDto;
import com.nasr.supportingsystemproject.dto.view.TicketAnswerViewDto;
import com.nasr.supportingsystemproject.dto.view.TicketViewDto;
import com.nasr.supportingsystemproject.exception.ResponseTemplate;
import com.nasr.supportingsystemproject.mapper.TicketAnswerMapper;
import com.nasr.supportingsystemproject.service.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@SecurityRequirement(name = "Bearer Authentication")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketAnswerMapper ticketAnswerMapper;

    /**
     * this api accessible by customer for get all ticket on customer panel
     * @param customerEmail as customerEmail
     * @return List of ticket that already create it
     */
    @PreAuthorize(value = "hasRole('customer')")
    @GetMapping("/{customerEmail}")
    public ResponseEntity<ResponseTemplate<List<TicketDto>>> getCustomerTickets(@PathVariable String customerEmail) {

        List<TicketDto> tickets = ticketService.getTicketByCustomerEmail(customerEmail);
        return ResponseEntity.ok(
                new ResponseTemplate<>(true, tickets, "ok")
        );
    }

    /**
     * this api accessible by customer for create new ticket and operate on later
     * @param dto as ticketInformation
     * @param customerEmail as customerEmail
     * @return ticket created
     */
    @PostMapping("/save/{customerEmail}")
    public ResponseEntity<?> saveTicket(@RequestBody @Valid TicketViewDto dto, @PathVariable String customerEmail) {
        TicketDto ticketDto = ticketService.saveOrUpdate(dto, customerEmail);
        return ResponseEntity.ok(
                new ResponseTemplate<>(true, ticketDto, "ticket saved successfully")
        );

    }

    /**
     * this api call by manger and supporter to get all ticket and operate on
     *
     * @return List<TicketDto> as result
     */
    @GetMapping("/all")
    public ResponseEntity<ResponseTemplate<List<TicketDto>>> getTickets() {

        return ResponseEntity.ok(
                new ResponseTemplate<>(true, ticketService.getTickets(), "ok")
        );
    }

    /**
     * this api accessible by support and can answer ticket which created by customer
     * @param dto as answer of support employee
     * @return ticketDto as result
     */
    @PutMapping("/saveAnswer")
    public ResponseEntity<?> saveTicketAnswer(@RequestBody @Valid TicketAnswerViewDto dto) {

        TicketDto ticketDto = ticketService.saveAnswer(ticketAnswerMapper.convertDtoToEntity(dto));
        return ResponseEntity.ok(
                new ResponseTemplate<>(true, ticketDto, "successfully ticket answered ")
        );
    }

    /**
     * @param id     as same as ticket id
     * @param status as same as ticketStatus contain( OPEN - COMPLETE)
     *               note - we cant change status to CLOSED on support panel but
     * @return updated ticket
     */
    @PutMapping("/changeTicketStatus/{id}")
    public ResponseEntity<ResponseTemplate<TicketDto>> updateTicketStatus(@PathVariable Long id, @RequestParam TicketStatus status) {
        TicketDto ticketDto = ticketService.updateStatus(id, status);
        return ResponseEntity.ok(
                new ResponseTemplate<>(true, ticketDto, "ticket status sucessfully changed")
        );
    }

    /**
     * this api accessible by manager and can to mange ticket such as remove or update
     * @param id as ticketId
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTemplate<?>> deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseTemplate<>(true, null, "ticket successfully deleted !")
        );
    }
}
