package com.example.FintechApplication.repositories;

import com.example.FintechApplication.dto.request.UserRequest;
import com.example.FintechApplication.model.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private static UserEntity onlyUser = new UserEntity();
    private final PasswordEncoder passwordEncoder;

    public UserRepository (
            PasswordEncoder passwordEncoder
    ){
        this.passwordEncoder = passwordEncoder;
    }


    public void saveUser(UserRequest newUserRequest){
        final  String hashedPassword = passwordEncoder.encode(newUserRequest.getPassword());
        onlyUser = new UserEntity(newUserRequest.getUsername(), hashedPassword);
    }

    public UserEntity getUser(){
        return onlyUser;
    }
}
