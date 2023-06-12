package com.unal.TeachingEvaluation.repositories;

import com.unal.TeachingEvaluation.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByRole(String role);

    Optional<Users>findByUsername(String username);
    @Query(value = "SELECT * FROM users WHERE username=? AND password=?", nativeQuery = true)
    Optional<Users> validateLogin(String username, String password);
}
