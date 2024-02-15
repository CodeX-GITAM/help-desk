package com.aarambh.helpdesk.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "queries")
public class QueryDAO {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID queryId;

    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false, updatable = false, referencedColumnName = "teamId")
    private TeamDAO teamId;

    @Column(name = "query", nullable = false, updatable = false)
    private String query;


    @Column(name="assignedTo")
    private String assignedTo;

    @Column(name="isSolved",nullable = false)
    @Builder.Default
    private boolean isSolved = false;
}
