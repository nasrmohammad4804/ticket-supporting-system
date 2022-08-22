package com.nasr.supportingsystemproject.domain;

import com.nasr.supportingsystemproject.base.domain.BaseEntity;
import com.nasr.supportingsystemproject.domain.enumeration.TicketStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket  extends BaseEntity<Long,String> {

    public static final String TICKET_STATUS  = "ticket_status";
    public static final String CUSTOMER_ID =  "customer_id";

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @Enumerated(value = EnumType.STRING)
    @Column(name = TICKET_STATUS)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = CUSTOMER_ID)
    private Customer customer;

    @Builder
    public Ticket(Long id, String answer) {
        super(id);
        this.answer = answer;
    }
}
