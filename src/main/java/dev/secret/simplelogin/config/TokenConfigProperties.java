package dev.secret.simplelogin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("token")
public record TokenConfigProperties(String signin_key) {
}
