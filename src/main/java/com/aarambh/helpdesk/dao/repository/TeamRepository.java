package com.aarambh.helpdesk.dao.repository;

import com.aarambh.helpdesk.dao.TeamDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamDAO, UUID> {
    Optional<TeamDAO> findByDeskNumber(int deskNumber);

    boolean existsByDeskNumber(int deskNumber);
}
