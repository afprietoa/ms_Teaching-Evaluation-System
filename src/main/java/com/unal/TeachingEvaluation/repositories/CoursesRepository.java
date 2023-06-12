package com.unal.TeachingEvaluation.repositories;

import com.unal.TeachingEvaluation.models.Courses;
import com.unal.TeachingEvaluation.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    Optional<Courses> findByName(String name);
}
