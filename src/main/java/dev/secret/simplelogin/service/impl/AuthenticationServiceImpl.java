package dev.secret.simplelogin.service.impl;

import dev.secret.simplelogin.dto.request.SignInRequest;
import dev.secret.simplelogin.dto.request.SignUpRequest;
import dev.secret.simplelogin.dto.response.JwtAuthenticationResponse;
import dev.secret.simplelogin.enums.Role;
import dev.secret.simplelogin.model.User;
import dev.secret.simplelogin.repository.UserRepository;
import dev.secret.simplelogin.service.AuthenticationService;
import dev.secret.simplelogin.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                     JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password())
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        return JwtAuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.surname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return JwtAuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
