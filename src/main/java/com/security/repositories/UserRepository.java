package com.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
