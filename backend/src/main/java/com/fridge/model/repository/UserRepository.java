package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
