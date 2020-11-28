package com.travelingbastards.travelingspring.controller;

import com.travelingbastards.travelingspring.model.Post;
import com.travelingbastards.travelingspring.service.post.PostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/admin/post")
public class PostController {

    @Resource
    private PostService postService;

    @GetMapping
    public List<Post> getPosts(){
        return postService.findAll();
    }

    @PostMapping("/{nickName}")
    public void createPost(@PathVariable("nickName") String nickName, @RequestBody Post post) {
        postService.createPost(post,nickName);
    }

    @GetMapping("/{nickName}")
    public List<Post> getPosts(@PathVariable("nickName") String nickName){
        return postService.findPostsByUser(nickName);
    }





}
