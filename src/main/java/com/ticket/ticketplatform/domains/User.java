package com.ticket.ticketplatform.domains;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.jca.support.LocalConnectionFactoryBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@Builder
public class User {

    @Id
    @Column(name="id",updatable=false,nullable=false)
    private UUID id;


    @Column(name="name",nullable=false)
    private String name;

    @Column(name="email",nullable=false,unique=true)
    private String email;

    @OneToMany(mappedBy="organizer",cascade=CascadeType.ALL)
    private List<Event> organizedEvents;



    @CreatedDate
    @Column(name="created_at",nullable=false,updatable=false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at",nullable = false,updatable = true)
    private LocalDateTime updatedAt;
}
