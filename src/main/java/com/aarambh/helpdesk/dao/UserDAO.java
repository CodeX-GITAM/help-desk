package com.aarambh.helpdesk.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "users")
public class UserDAO {
    @Id
    @UuidGenerator
    @GeneratedValue(generator = "UUID")
    private UUID uid;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name="password",nullable = false)
    private String password;
}
