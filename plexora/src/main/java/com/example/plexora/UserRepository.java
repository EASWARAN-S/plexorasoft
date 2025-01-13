package com.example.plexora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(Long userId);
    boolean existsByPhone(String phone);
    User findByPhone(String phone);
    boolean existsByEmail(String email);
}