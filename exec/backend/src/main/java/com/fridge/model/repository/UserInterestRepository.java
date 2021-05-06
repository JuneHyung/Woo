package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.UserInterest;

public interface UserInterestRepository extends JpaRepository<UserInterest, Integer> {

	UserInterest findByUser_idAndPost_id(int user_id, int post_id);

}
