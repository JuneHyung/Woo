package com.fridge.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import com.fridge.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Post findByIdAndUser_id(int postId, int parseInt);

//	@Query(value = "SELECT * FROM post where user_id = user_id", nativeQuery = true)
	List<Post> findByUser_id(int user_id , Pageable pegeable);
}
