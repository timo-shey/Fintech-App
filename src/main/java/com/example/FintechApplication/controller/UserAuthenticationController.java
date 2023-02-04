package com.example.FintechApplication.controller;

import com.example.FintechApplication.dto.request.AuthentificationRequest;
import com.example.FintechApplication.dto.response.AuthentificationResponse;
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

  @PostMapping("/authentification")
  public ResponseEntity<AuthentificationResponse> doAuthentification(
    @RequestBody AuthentificationRequest request
  ) throws Exception{
    Optional<AuthentificationResponse> response = userAuthenticationService.authentificate(request);
    return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

}