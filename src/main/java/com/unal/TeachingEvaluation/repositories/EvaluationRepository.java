package com.unal.TeachingEvaluation.repositories;

import com.unal.TeachingEvaluation.models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    Optional<Evaluation> findByDate(String date);

    Optional<Evaluation> findBySemester(String semester);
}
