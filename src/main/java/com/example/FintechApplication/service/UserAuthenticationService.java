package com.example.FintechApplication.service;

import com.example.FintechApplication.dto.request.AuthenticationRequest;
import com.example.FintechApplication.dto.response.AuthenticationResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserAuthenticationService {
    Optional<AuthenticationResponse> authenticate(AuthenticationRequest request) throws Exception;
}

