package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
