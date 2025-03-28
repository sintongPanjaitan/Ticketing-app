package com.test.ticketing.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(unique = true, nullable = false, name = "identity_number")
    private String identity_number;

    @Column(unique = true, nullable = false, name = "phone_number")
    private String phone_number;

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

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTicket> userTickets;

    public Users() {
    }

    public Users(String name, String identity_number, String phone_number, LocalDateTime created_at, LocalDateTime modifed_at, Boolean is_deleted) {
        this.name = name;
        this.identity_number = identity_number;
        this.phone_number = phone_number;
        this.created_at = created_at;
        this.modifed_at = modifed_at;
        this.is_deleted = is_deleted;
    }

//    public Users(long id, String name, String identity_number, String phone_number, LocalDateTime created_at, LocalDateTime modifed_at, Boolean is_deleted) {
//        this.id = id;
//        this.name = name;
//        this.identity_number = identity_number;
//        this.phone_number = phone_number;
//        this.created_at = created_at;
//        this.modifed_at = modifed_at;
//        this.is_deleted = is_deleted;
//    }

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

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identity_number='" + identity_number + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", created_at=" + created_at +
                ", modifed_at=" + modifed_at +
                ", deleted_at=" + deleted_at +
                ", is_deleted=" + is_deleted +
                '}';
    }
}
