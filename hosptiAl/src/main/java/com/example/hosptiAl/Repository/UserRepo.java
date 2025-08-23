package com.example.hosptiAl.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosptiAl.Model.User;

public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

}
