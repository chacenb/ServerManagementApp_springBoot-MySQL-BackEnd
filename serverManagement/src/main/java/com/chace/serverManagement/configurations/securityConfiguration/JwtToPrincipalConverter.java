package com.chace.serverManagement.configurations.securityConfiguration;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class JwtToPrincipalConverter {

  public UserPrincipal convert(DecodedJWT jwt) {
    return UserPrincipal.builder()
                        .userId(Long.valueOf(jwt.getSubject()))
                        .login(jwt.getClaim("login").asString())
                        .password(jwt.getClaim("password").asString())
                        .authorities(extractAuthoritesFromClaim(jwt))
                        .build();
  }


  private List<SimpleGrantedAuthority> extractAuthoritesFromClaim(DecodedJWT jwt) {
    Claim auths = jwt.getClaim("roles");
    return (auths.isNull() || auths.isMissing()) ? List.of() : auths.asList(SimpleGrantedAuthority.class);
  }
}
