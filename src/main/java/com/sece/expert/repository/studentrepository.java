package com.sece.expert.repository;

import com.sece.expert.entity.studententity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface studentrepository extends JpaRepository<studententity, Integer> {
    Optional<studententity> findByUsername(String username);
}
