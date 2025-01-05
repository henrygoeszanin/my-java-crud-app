package com.myjavacrudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myjavacrudapp.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}