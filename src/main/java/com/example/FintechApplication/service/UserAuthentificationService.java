package com.example.FintechApplication.service;

import com.example.FintechApplication.dto.request.AuthentificationRequest;
import com.example.FintechApplication.dto.response.AuthentificationResponse;

import java.util.Optional;

public interface UserAuthentificationService {
    Optional<AuthentificationResponse> authentificate(AuthentificationRequest request) throws Exception;
}
