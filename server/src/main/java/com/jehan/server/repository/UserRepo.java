package com.jehan.server.repository;

import com.jehan.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo  extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
