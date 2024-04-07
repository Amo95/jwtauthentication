package dev.secret.simplelogin.dto.response;

import lombok.Builder;

@Builder
public record JwtAuthenticationResponse(String token) {
}
