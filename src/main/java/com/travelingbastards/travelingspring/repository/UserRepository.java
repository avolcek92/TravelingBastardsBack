package com.travelingbastards.travelingspring.repository;


import com.travelingbastards.travelingspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByNickName(String nickName);

    User findByActivationCode(String code);
}
