package com.test.ticketing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "place")
    private String place;

    @Column(nullable = false, name = "maximum_tickets")
    private Integer maximum_tickets;

    @Column(name = "event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate event_date;

//    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name="description", length=512)
    private String description;

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

    @OneToMany(mappedBy = "events", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tickets> tickets;

    @OneToMany(mappedBy = "events", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTicket> userTickets;


    public Events() {
    }

    public Events(String name, String place, Integer maximum_tickets, LocalDate event_date, String description, LocalDateTime created_at, LocalDateTime modifed_at, Boolean is_deleted) {
        this.name = name;
        this.place = place;
        this.maximum_tickets = maximum_tickets;
        this.event_date = event_date;
        this.description = description;
        this.created_at = created_at;
        this.modifed_at = modifed_at;
        this.is_deleted = is_deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getMaximum_tickets() {
        return maximum_tickets;
    }

    public void setMaximum_tickets(Integer maximum_tickets) {
        this.maximum_tickets = maximum_tickets;
    }

    public LocalDate getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getModifed_at() {
        return modifed_at;
    }

    public void setModifed_at(LocalDateTime modifed_at) {
        this.modifed_at = modifed_at;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", maximum_tickets=" + maximum_tickets +
                ", event_date=" + event_date +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", modifed_at=" + modifed_at +
                ", deleted_at=" + deleted_at +
                ", is_deleted=" + is_deleted +
                '}';
    }
}
