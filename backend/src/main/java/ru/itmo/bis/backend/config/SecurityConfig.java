package ru.itmo.bis.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itmo.bis.backend.constant.UserRole;
import ru.itmo.bis.backend.security.JwtAuthenticationEntryPoint;
import ru.itmo.bis.backend.security.JwtAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationEntryPoint unauthorizedHandler;

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  private final String LANDLORD = UserRole.LANDLORD.name();

  private final String ARTIST = UserRole.ARTIST.name();

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authConfig
  ) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(request -> {
          var corsConfig = new org.springframework.web.cors.CorsConfiguration();
          corsConfig.setAllowedOrigins(List.of("*")); //TODO: изменить на фронт
          corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
          corsConfig.setAllowedHeaders(List.of("Authorization", "Content-Type"));
          corsConfig.setExposedHeaders(List.of("Authorization"));
          corsConfig.setAllowCredentials(true);
          return corsConfig;
        }))
        .httpBasic(AbstractHttpConfigurer::disable)
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint(unauthorizedHandler)
        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/artists/**").hasAnyAuthority(ARTIST, LANDLORD)
            .requestMatchers("/artists/**").hasAuthority(ARTIST)
            .requestMatchers(HttpMethod.GET, "/landlords/**").hasAnyAuthority(ARTIST, LANDLORD)
            .requestMatchers("/landlords/**").hasAuthority(LANDLORD)
            .anyRequest().authenticated()
        );
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
