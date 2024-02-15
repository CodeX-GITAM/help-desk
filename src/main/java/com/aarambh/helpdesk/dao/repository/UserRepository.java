package com.aarambh.helpdesk.dao.repository;

import com.aarambh.helpdesk.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDAO, UUID> {
    Optional<UserDAO> findByUsername(String username);
}
