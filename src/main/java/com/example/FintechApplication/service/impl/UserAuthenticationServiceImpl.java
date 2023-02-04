package com.example.FintechApplication.service.impl;

import com.example.FintechApplication.dto.request.AuthentificationRequest;
import com.example.FintechApplication.dto.response.AuthentificationResponse;
import com.example.FintechApplication.service.UserAuthenticationService;
import com.example.FintechApplication.service.UserService;
import com.example.FintechApplication.utilities.JWTTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
@AllArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTTokenUtil jwtTokenUtil;
    @Override
    public Optional<AuthentificationResponse> authentificate(AuthentificationRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return Optional.of(new AuthentificationResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
