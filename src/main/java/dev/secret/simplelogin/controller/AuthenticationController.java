package dev.secret.simplelogin.controller;

import dev.secret.simplelogin.dto.request.SignInRequest;
import dev.secret.simplelogin.dto.request.SignUpRequest;
import dev.secret.simplelogin.dto.response.JwtAuthenticationResponse;
import dev.secret.simplelogin.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "signin endpoint", description = "log into application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged into"),
            @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/signup")
    @Operation(summary = "signup endpoint", description = "create new user application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged into"),
            @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }
}
