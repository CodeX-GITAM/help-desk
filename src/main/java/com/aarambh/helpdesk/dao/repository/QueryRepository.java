package com.aarambh.helpdesk.dao.repository;

import com.aarambh.helpdesk.dao.QueryDAO;
import com.aarambh.helpdesk.dao.TeamDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QueryRepository extends JpaRepository<QueryDAO, UUID> {
    //@Query("SELECT COUNT(q) FROM QueryDAO q WHERE q.teamId = :team AND q.isSolved = true")
    List<QueryDAO> findAllByTeamId(TeamDAO team);

    @Query("SELECT COUNT(q) FROM QueryDAO q WHERE q.teamId = :team AND q.isSolved = false")
    long countByTeamIdAndIsSolvedFalse(TeamDAO team);

    void deleteAllByTeamId(TeamDAO teamId);

    boolean existsByTeamId(TeamDAO teamById);
}
