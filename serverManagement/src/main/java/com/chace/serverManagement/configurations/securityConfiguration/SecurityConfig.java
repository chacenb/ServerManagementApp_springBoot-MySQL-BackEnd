package com.chace.serverManagement.configurations.securityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain applicationSecurityFilterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(configurer -> configurer.disable()) // If our stateless API uses token-based authentication, such as JWT, no need CSRF protection
      .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // REST API should be stateless
      .formLogin(configurer -> configurer.disable())  // We create an API, so no need the default login page provided by spring security
      .securityMatcher("api/v2/server/**") // this config makes this whole security work on the pattern provided (whole application = /**)
      .authorizeHttpRequests(registry -> registry
        .requestMatchers("api/v2/server").permitAll() // the root url accessible to everybody
        .anyRequest().authenticated() // any other request should by an authenticated user
      )



      .build();
  }
}
