package com.travelingbastards.travelingspring.controller;

import com.travelingbastards.travelingspring.model.User;
import com.travelingbastards.travelingspring.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class AdminController {

    @Resource
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping ("/{nickName}")
    public User getUserByNickName(@PathVariable("nickName") String nickName){
        User user = userService.findByNickName(nickName);
        return user;
    }


    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
    }

}
