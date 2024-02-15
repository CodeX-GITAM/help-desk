package com.aarambh.helpdesk.dao.repository;

import com.aarambh.helpdesk.dao.RatingDAO;
import com.aarambh.helpdesk.dao.TeamDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<RatingDAO, UUID> {


    List<RatingDAO> findAllByTeam(TeamDAO teamDAO);

    void deleteAllByTeam(TeamDAO teamById);

    boolean existsByTeam(TeamDAO teamById);
}
