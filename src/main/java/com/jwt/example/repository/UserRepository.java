package com.jwt.example.repository;


import com.jwt.example.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET updated_time = NOW(), version = version + 1 WHERE username = :username", nativeQuery = true)
    int updateDate(@Param("username") String username);

}
