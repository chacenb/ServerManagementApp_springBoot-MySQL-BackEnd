package com.chace.serverManagement.serverConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS config on a Spring application: https://howtodoinjava.com/spring-boot2/spring-cors-configuration/
 * *************
 * 1- LOCAL LEVEL : class or method level
 * -- Using "@CrossOrigin" annotation on class(w/ @Controller) or on method.
 * -- It allows controlling the CORS configuration at the “method level”.
 * 2- GLOBAL / APPLICATION LEVEL: define the CORS configuration at the “global level”
 * --  Using WebMvcConfigurer Bean (recommended) :
 * --  Overriding "addCorsMappings(CorsRegistry registry)" on WebMvcConfigurer bean.
 */
//@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer { //  extends WebSecurityConfigurerAdapter

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/api/v2/server/**") // or .addMapping("/**")
            .allowedOrigins("http://localhost:4200", "http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS", "DELETE", "HEAD") // or .allowedMethods("*")
            .allowedHeaders("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers")
            .exposedHeaders("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename")
            .allowCredentials(true)
            .maxAge(3600);
      }
    };
  }

//    CORS WITH SPRING SECURITY
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//        //other config
//    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("https://example.com"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return (CorsConfigurationSource) source;
//    }
}


