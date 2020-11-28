package com.travelingbastards.travelingspring.service.user;

import com.travelingbastards.travelingspring.model.Role;
import com.travelingbastards.travelingspring.model.Status;
import com.travelingbastards.travelingspring.model.User;

import com.travelingbastards.travelingspring.repository.UserRepository;

import com.travelingbastards.travelingspring.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder, MailSender mailSender) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Value("${activation.url}")
    private String activationUrl;


    @Override
    public User registerUser(User user) {
        Role roleUser = roleService.findByRoleName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.NOT_ACTIVE);
        user.setActivationCode(UUID.randomUUID().toString());
        User registeredUser = userRepository.save(user);


            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Traveling Bastards! \n" +
                            "Please , visit next link:" + activationUrl +"/%s to activate your user",
                    user.getFirstName(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation Code", message);
            log.info("Mail send successful!");


        log.info("IN registerUser -  successfully registered with nickname: {}", registeredUser.getNickName());
        return registeredUser;
    }

    @Override
    public List<User> findAll() {
        List<User> result = userRepository.findAll();
        log.info("IN findAll - {} users found", result.size());
        return result;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("IN deleteUser - user with id: {} successfully deleted", id);
    }
    @Override
    public User findById(long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user ID: " + id));
        log.info("IN findById - found by id: {}", id);
        return user;
    }

    public void updateUser(long id, User user){
        User userFromDB = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user ID: " + id));
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setNickName(user.getNickName());
        userFromDB.setEmail(user.getEmail());
        userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
        userFromDB.setPhotoName(user.getPhotoName());
        userFromDB.setUserDescription(user.getUserDescription());
        userFromDB.setPost(user.getPost());
        userFromDB.setRoles(user.getRoles());
        userRepository.save(userFromDB);
        log.info("IN updateUser - user: {} successfully updated", userFromDB);
    }

    @Override
    public User findByNickName(String nickName) {
        User user = userRepository.findByNickName(nickName);
        log.info("IN findByUsername -  found by nickname: {}", nickName);
        return user;
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user == null ){
            return false;
        }
        user.setActivationCode(null);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return true;
    }
}
