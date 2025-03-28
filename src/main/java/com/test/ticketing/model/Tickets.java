package com.test.ticketing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false) // Foreign Key column
    private Events events;

    @Column(nullable = false, name = "maximum_tickets")
    private Integer maximum_tickets;

    @Column(nullable = false, name = "price")
    private Long price;

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start_at;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end_at;

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

    @Version
    private Integer version;

    public Tickets() {
    }

    public Tickets(Events events, Integer maximum_tickets, Long price, LocalDateTime start_at, LocalDateTime end_at, LocalDateTime created_at, LocalDateTime modifed_at, Boolean is_deleted) {
        this.events = events;
        this.maximum_tickets = maximum_tickets;
        this.price = price;
        this.start_at = start_at;
        this.end_at = end_at;
        this.created_at = created_at;
        this.modifed_at = modifed_at;
        this.is_deleted = is_deleted;
    }


}
