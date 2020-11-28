package com.travelingbastards.travelingspring.service.user;

import com.travelingbastards.travelingspring.model.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);

    List<User> findAll();

    void deleteUser(Long id);

    User findById(long id);

    void updateUser(long id, User user);

    User findByNickName(String nickName);

    boolean activateUser(String code);
}
