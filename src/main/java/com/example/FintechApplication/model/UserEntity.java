package com.example.FintechApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class UserEntity {
    private String userId;
    private String username;
    private String hashedPassword;

    public UserEntity(UserEntity user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.hashedPassword = user.getHashedPassword();
    }

    public UserEntity(String username, String hashedPassword) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.hashedPassword = hashedPassword;
    }
}
