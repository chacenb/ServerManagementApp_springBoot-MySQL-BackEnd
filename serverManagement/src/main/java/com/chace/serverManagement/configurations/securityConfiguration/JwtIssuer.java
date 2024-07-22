package com.chace.serverManagement.configurations.securityConfiguration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/* When a token is decoded, it has to be decoded into an object called User Principal that will represent a user */

@Component
public class JwtIssuer {

  @Value("${security.jwt.secret-key}")
  private String secretKey;

  public String issue(Long userId, String email, List<String> roles) {
    return JWT.create()
              .withSubject(String.valueOf(userId))
              .withClaim("login", email)
              .withClaim("password", email)
              .withClaim("roles", roles)
              .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
              .sign(Algorithm.HMAC256(secretKey));
  }
}
