package com.chace.serverManagement.controller;

import com.chace.serverManagement.configurations.securityConfiguration.JwtIssuer;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j /* Slf4j: Simple Logging Facade for Java : see codeBlocks */
@RestController /* show that class is going to serve rest endpoints api-s, mostly used with @RequestMapping. */
@RequestMapping(path = "api/v2/server/auth")  /* used to map the web requests */
@RequiredArgsConstructor /* generates constructor for all final & @NonNull fields. Thus handles dependency injection */
public class AuthController {

  private final JwtIssuer jwtIssuer;

  public record LoginCredentials(String login, String password) {
  }

  @Getter @Setter @Builder
  public static class LoginResponse {
    private final String token;
  }


  /**
   * No user is configured in this application, so we generate a token for a fake user to send back to the requester
   */
  @PostMapping(path = "/login")
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginCredentials credentials) {

    return ResponseEntity.ok(LoginResponse.builder().token(this.generateDummyToken(credentials)).build());
  }


  private String generateDummyToken(LoginCredentials credentials) {
    return jwtIssuer.issue(1L, credentials.login, List.of("DEFAULT_ROLE"));
  }

}
