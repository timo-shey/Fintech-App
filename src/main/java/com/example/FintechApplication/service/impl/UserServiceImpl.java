package com.example.FintechApplication.service.impl;

import com.example.FintechApplication.model.UserEntity;
import com.example.FintechApplication.repositories.UserRepository;
import com.example.FintechApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        userRepository.saveUser(new UserRequest(user, password)); // always saving the hardcoded user first to ensure the user is present ( later the user must be in the database and will be queried. Now i only call that user after saving it)

        UserEntity user = userRepository.getUser();
        //if the save authorized user is equal to the on asking for permission return that user.
        if (user.getUsername().equals(username)) {
            return new User(user.getUsername(), user.getHashedPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
