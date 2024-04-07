package dev.secret.simplelogin.service;

import dev.secret.simplelogin.dto.request.SignInRequest;
import dev.secret.simplelogin.dto.request.SignUpRequest;
import dev.secret.simplelogin.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signin(SignInRequest request);
    JwtAuthenticationResponse signup(SignUpRequest request);
}
