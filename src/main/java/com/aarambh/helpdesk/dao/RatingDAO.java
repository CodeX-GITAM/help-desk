package com.aarambh.helpdesk.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "ratings")
public class RatingDAO {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID ratingId;

    @Column(name = "rating", nullable = false,updatable = false)
    private double rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "teamId", nullable = false, updatable = false)
    private TeamDAO team;

    @Column(name = "ratingBy", nullable = false, updatable = false)
    private String ratingBy;

    @Column(name="createdOn",nullable = false,updatable = false)
    @Builder.Default
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "comment", nullable = false, updatable = false)
    private String comment;


}
