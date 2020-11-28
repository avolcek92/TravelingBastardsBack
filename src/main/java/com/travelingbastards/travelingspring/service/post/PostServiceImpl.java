package com.travelingbastards.travelingspring.service.post;

import com.travelingbastards.travelingspring.model.Post;
import com.travelingbastards.travelingspring.model.User;
import com.travelingbastards.travelingspring.repository.PostRepository;
import com.travelingbastards.travelingspring.repository.UserRepository;
import com.travelingbastards.travelingspring.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostRepository postRepository;

    @Resource
    private UserService userService;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Post findById(long id) {
       Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid post ID: " + id));
        return post;
    }

    @Override
    public void createPost(Post post, String userNick) {
        User user = userService.findByNickName(userNick);
        post.setUser(user);
        postRepository.save(post);

    }

    @Override
    public List<Post> findPostsByUser(String userNick) {
        User user = userService.findByNickName(userNick);
        List<Post> postFullList = postRepository.findAll();
        List<Post> userPostList = new ArrayList<>();
        for (Post post : postFullList) {
            if(user.getId().equals(post.getUser().getId())){
                userPostList.add(post);
            }
        }
        return userPostList;
    }
}
