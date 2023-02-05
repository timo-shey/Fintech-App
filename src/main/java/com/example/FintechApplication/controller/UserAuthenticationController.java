package com.example.FintechApplication.controller;

import com.example.FintechApplication.dto.request.AuthenticationRequest;
import com.example.FintechApplication.dto.response.AuthenticationResponse;
import com.example.FintechApplication.service.UserAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserAuthenticationController {
  private final UserAuthenticationService userAuthenticationService;

  @PostMapping("/authentication")
  public ResponseEntity<AuthenticationResponse> doAuthentication(
    @RequestBody AuthenticationRequest request
  ) throws Exception{
    Optional<AuthenticationResponse> response = userAuthenticationService.authenticate(request);
    return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

}