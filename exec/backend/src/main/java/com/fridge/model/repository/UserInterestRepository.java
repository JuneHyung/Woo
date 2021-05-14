package com.fridge.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.UserInterest;

public interface UserInterestRepository extends JpaRepository<UserInterest, Integer> {

	Optional<UserInterest> findByUserIdAndPostId(int userId, int postId);

}
