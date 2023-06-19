package com.example.Spring.Security.repository;

import com.example.Spring.Security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    Boolean existByUsername(String username);
//    Boolean  existByEmail(String email);
//    Optional<UserEntity> findByUsername(String username);
//    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity u where u.username = ?1 or u.email = ?1")
    Optional<UserEntity> findByUsernameOrEmail(String usernameOrEmail);

    @Query("select u from UserEntity u where u.username = ?1 or u.email = ?2")
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    @Query("select (count(u) > 0) from UserEntity u where u.username = ?1 or u.email = ?2")
    Boolean existsByUsernameOrEmail(String username, String email);




}
