package com.example.SpringSecurity6WithSpringBoot.repository;

import com.example.SpringSecurity6WithSpringBoot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
