package com.travelingbastards.travelingspring.repository;

import com.travelingbastards.travelingspring.model.Post;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface PostRepository extends CrudRepository <Post, Long> {
    List<Post> findAll();
}
