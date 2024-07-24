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
public class _WebSecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  /**
   *  We define here the behavior of our security policy
   * by configuring a bean of type "SecurityFilterChain" */
  @Bean
  public SecurityFilterChain applicationSecurityFilterChain(HttpSecurity http) throws Exception {

    /* We add our custom security filter "JwtAuthenticationFilter" in Spring's filter chain just before the "UsernamePasswordAuthenticationFilter" */
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http
        .csrf(configurer -> configurer.disable()) // If our stateless API uses token-based authentication, such as JWT, no need CSRF protection
        .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // REST API should be stateless
        .formLogin(configurer -> configurer.disable())  // We create an API, so no need the default login page provided by spring security
        .securityMatcher("api/v2/server/**") // this config makes this whole security work on the pattern provided (whole application = /**)
        .authorizeHttpRequests(registry -> registry
            .requestMatchers("api/v2/server/public/**").permitAll() // the root url accessible to everybody
            .requestMatchers("api/v2/server/auth/**").permitAll() // the login url accessible to everybody
            .anyRequest().authenticated())
        .build();
  }
}
