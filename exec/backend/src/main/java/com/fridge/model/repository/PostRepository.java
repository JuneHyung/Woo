package com.fridge.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Optional<Post> findByIdAndUserId(int postId, int parseInt);

//	@Query(value = "SELECT * FROM post where user_id = user_id", nativeQuery = true)
	List<Post> findByUserId(int userId, Pageable pegeable);

}
