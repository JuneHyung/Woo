package com.fridge.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fridge.model.Post;
import com.fridge.model.dto.PostDto;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Post findByIdAndUser_id(int postId, int parseInt);

//	@Query(value = "SELECT * FROM post where user_id = user_id", nativeQuery = true)
	List<Post> findByUser_id(int userId, Pageable pegeable);

}
