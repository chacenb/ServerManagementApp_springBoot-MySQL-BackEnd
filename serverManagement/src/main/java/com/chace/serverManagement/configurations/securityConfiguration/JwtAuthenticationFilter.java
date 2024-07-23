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

  private final JwtTokenIssuerDecoder        jwtTokenDecoder;
  private final JwtTokenToPrincipalConverter jwtTokenToPrincipalConverter;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    /* get the token string from the incoming request */
    this.extractTokenFromRequestHeader(request)

      /* decode it into a "DecodedJWT" */
      .map(jwtTokenDecoder::decode)

      /* Convert the "DecodedJWT" to a Principal */
      .map(jwtTokenToPrincipalConverter::convert)

      /* Wrap the Principal into "UserPrincipalAuthenticationToken" to fill other parameters */
      .map(UserPrincipalAuthenticationToken::new)

      /* if everything is OK, Set the currently authenticated principal in the Security context */
      .ifPresent(userPrincAuthToken -> SecurityContextHolder.getContext().setAuthentication(userPrincAuthToken));


    /* apply our custom filter to the request*/
    filterChain.doFilter(request, response);
  }


  /**
   * Util internal method just to extract token from the authorization header of the incoming request
   * The token can be present inside a request header or NOT
   */
  private Optional<String> extractTokenFromRequestHeader(HttpServletRequest request) {
    var token = request.getHeader("Authorization");
    if (StringUtils.hasText(token) && token.startsWith("Bearer ")) return Optional.of(token.substring(7)); // drop the string "Bearer " from the gotten value
    return Optional.empty();
  }
}
