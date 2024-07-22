package com.chace.serverManagement.configurations.securityConfiguration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  /* This bean of type "SecurityFilterChain" is the one defining te security policy of the application
   * Here we configure the behavior of our security policy */
  @Bean
  public SecurityFilterChain applicationSecurityFilterChain(HttpSecurity http) throws Exception {

    /* Add our custom filter in the filter chain of Spring */
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http
        .csrf(configurer -> configurer.disable()) // If our stateless API uses token-based authentication, such as JWT, no need CSRF protection
        .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // REST API should be stateless
        .formLogin(configurer -> configurer.disable())  // We create an API, so no need the default login page provided by spring security
        .securityMatcher("api/v2/server/**") // this config makes this whole security work on the pattern provided (whole application = /**)
        .authorizeHttpRequests(registry -> registry
            .requestMatchers("api/v2/server").permitAll() // the root url accessible to everybody
            .requestMatchers("api/v2/server/auth/login").permitAll() // the login url accessible to everybody
            .anyRequest().authenticated())
        .build();
  }
}
