package com.chace.serverManagement.configurations.securityConfiguration;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Builder
public class UserPrincipal implements UserDetails {

  private Long                                   userId;
  private String                                 login;
  private String                                 password;
  private Collection<? extends GrantedAuthority> authorities;

  @Override public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override public String getPassword() {
    return password;
  }

  @Override public String getUsername() {
    return login;
  }

  @Override public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
