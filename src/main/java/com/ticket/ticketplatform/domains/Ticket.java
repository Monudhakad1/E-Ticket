package com.ticket.ticketplatform.domains;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @Column(name="id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="status",nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatusEnum status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ticket_type_id", nullable = false)
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="purchaser_id", nullable = false)
    private User purchaser;

//validation
    @OneToMany(mappedBy="ticket", cascade = CascadeType.ALL)
    private List<TicketValidation> validations= new ArrayList<>();

    @OneToMany(mappedBy="ticket", cascade = CascadeType.ALL)
    private List<QrCode> qrCodes= new ArrayList<>();

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(name="updated_at", nullable = false, updatable = true)
    private LocalDateTime updated_at;
}
