package com.unal.TeachingEvaluation.repositories;

import com.unal.TeachingEvaluation.models.Students;
import com.unal.TeachingEvaluation.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {
    Optional<Students> findByEmail(String email);

    Optional<Students>findByName(String name);
}
