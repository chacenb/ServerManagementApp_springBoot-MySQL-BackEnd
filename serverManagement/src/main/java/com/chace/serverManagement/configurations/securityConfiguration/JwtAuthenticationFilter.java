package com.chace.serverManagement.configurations.securityConfiguration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtDecoder              jwtDecoder;
  private final JwtToPrincipalConverter jwtToPrincipalConverter;

  @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    extractTokenFromRequestHeader(request)
        .map(jwtDecoder::decode)
        .map(jwtToPrincipalConverter::convert)
        .map(UserPrincipalAuthenticationToken::new)
        .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));

    filterChain.doFilter(request, response);
  }


  /**
   * EXtract token from the authorization header of the incoming request
   * The token can be present inside a request header or NOT
   */
  private Optional<String> extractTokenFromRequestHeader(HttpServletRequest request) {
    var token = request.getHeader("Authorization");
    if (StringUtils.hasText(token) && token.startsWith("Bearer ")) return Optional.of(token.substring(7)); // drop the string "Bearer " from the gotten value
    return Optional.empty();
  }
}
