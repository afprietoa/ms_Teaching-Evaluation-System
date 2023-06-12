package com.unal.TeachingEvaluation.repositories;

import com.unal.TeachingEvaluation.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Integer> {
    Optional<Teachers> findByEmail(String email);

    Optional<Teachers>findByName(String name);

}
