package com.test.ticketing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users_tickets")
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "ticket_code")
    private String ticket_code;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false) // Foreign Key column
    private Events events;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign Key column
    private Users users;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created_at;

    @Column(name = "modifed_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifed_at;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deleted_at;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    public UserTicket() {
    }

    public UserTicket(String ticket_code, Events events, Users users, LocalDateTime created_at, LocalDateTime modifed_at, Boolean is_deleted) {
        this.ticket_code = ticket_code;
        this.events = events;
        this.users = users;
        this.created_at = created_at;
        this.modifed_at = modifed_at;
        this.is_deleted = is_deleted;
    }



}
