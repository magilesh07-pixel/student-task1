package com.sece.student.repository;

import com.sece.student.entity.Studententity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Studententity, Integer> {
    java.util.Optional<Studententity> findByUsername(String username);
}
