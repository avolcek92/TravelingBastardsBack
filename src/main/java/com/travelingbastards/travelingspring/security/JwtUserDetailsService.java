package com.travelingbastards.travelingspring.security;

import com.travelingbastards.travelingspring.model.User;
import com.travelingbastards.travelingspring.security.jwt.JwtUser;
import com.travelingbastards.travelingspring.security.jwt.JwtUserFactory;
import com.travelingbastards.travelingspring.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserService userService;

    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = userService.findByNickName(nickName);

        if (user == null) {
            throw new UsernameNotFoundException("User with nickname: " + nickName + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with nickname: {} successfully loaded", nickName);
        return jwtUser;
    }
}
