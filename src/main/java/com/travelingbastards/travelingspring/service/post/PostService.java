package com.travelingbastards.travelingspring.service.post;

import com.travelingbastards.travelingspring.model.Post;
import com.travelingbastards.travelingspring.model.User;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    void savePost(Post post);

    void deletePost(Post post);

    Post findById(long id);

    void createPost(Post post, String userNick);

    List<Post> findPostsByUser (String userNick);

}
