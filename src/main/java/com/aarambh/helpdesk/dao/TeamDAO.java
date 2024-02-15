package com.aarambh.helpdesk.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Setter
@Table(name = "teams")
public class TeamDAO {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID teamId;

    @Column(name = "deskNumber", nullable = false, unique = true)
    private int deskNumber;

    @Column(name="teamName",nullable = false)
    private String teamName;

    @Column(name="rating",nullable = false)
    @Builder.Default
    private double rating=0;
}
