package com.fridge.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByOrderByDateDesc();

}
