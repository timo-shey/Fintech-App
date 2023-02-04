package com.example.FintechApplication.service;

import com.example.FintechApplication.dto.request.AuthentificationRequest;
import com.example.FintechApplication.dto.response.AuthentificationResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserAuthenticationService {
    Optional<AuthentificationResponse> authentificate(AuthentificationRequest request) throws Exception;
}
