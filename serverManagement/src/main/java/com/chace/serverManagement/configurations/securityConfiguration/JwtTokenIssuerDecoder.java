package com.chace.serverManagement.configurations.securityConfiguration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/* When a token is decoded, it has to be decoded into an object called User Principal that will represent a user */

@Component
public class JwtTokenIssuerDecoder {

  @Value("${security.jwt.secret-key}")
  private String    secretKey;
  private Algorithm signingAlgorithm;

  @PostConstruct
  public void init() {
    signingAlgorithm = Algorithm.HMAC256(this.secretKey);
  }

  public String issue(Long userId, String email, List<String> roles) {
    return JWT.create()
      .withSubject(String.valueOf(userId))
      .withClaim("login", email)
      .withClaim("password", email)
      .withClaim("roles", roles)
      .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
      .sign(signingAlgorithm);
  }

  public DecodedJWT decode(String token) {
    return JWT.require(signingAlgorithm).build().verify(token);
  }

}
