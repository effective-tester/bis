package ru.itmo.bis.backend.config;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.bis.backend.config.property.JwtProperties;

import java.security.Key;

@Configuration
@Getter
@RequiredArgsConstructor
public class JwtConfiguration {

    private final JwtProperties jwtProperties;

    @Bean
    public Key generateKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }
}
