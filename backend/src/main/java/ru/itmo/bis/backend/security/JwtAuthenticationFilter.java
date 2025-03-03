package ru.itmo.bis.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itmo.bis.backend.config.JwtConfiguration;
import ru.itmo.bis.backend.exception.security.SecurityContextException;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtConfiguration jwtConfig;

  private final JwtTokenProvider jwtTokenProvider;

  private final BisUserDetailsService taskUserDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    try {
      getJwtFromRequest(request)
          .filter(jwtTokenProvider::validateToken)
          .ifPresent(jwt -> {
            String email = jwtTokenProvider.getUserEmailFromJWT(jwt);
            UserDetails userDetails = taskUserDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
          });
    } catch (Exception e) {
      throw new SecurityContextException();
    }
    filterChain.doFilter(request, response);
  }

  private Optional<String> getJwtFromRequest(HttpServletRequest request) {
    var jwtProperties = jwtConfig.getJwtProperties();
    String prefix = jwtProperties.getPrefix();
    return Optional.ofNullable(request.getHeader(jwtProperties.getHeader()))
        .filter(bearerToken ->
            StringUtils.hasText(bearerToken) && bearerToken.startsWith(prefix))
        .map(bearerToken -> bearerToken.substring(prefix.length()));
  }
}
