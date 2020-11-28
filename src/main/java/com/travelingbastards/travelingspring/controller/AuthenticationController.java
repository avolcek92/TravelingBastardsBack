package com.travelingbastards.travelingspring.controller;

import com.travelingbastards.travelingspring.dto.AuthenticationRequestDto;
import com.travelingbastards.travelingspring.model.User;
import com.travelingbastards.travelingspring.security.jwt.JwtTokenProvider;
import com.travelingbastards.travelingspring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/authentication/")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getNickName();;
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByNickName(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    @PostMapping("registration")
    public String createUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User " + user.getNickName() + " successfully registered";
    }

    @GetMapping("activate/{code}")
    public String activateByEmail(@PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){

            return "User is activated";
        }else{
            return "activation code is not found";
        }

    }


}
