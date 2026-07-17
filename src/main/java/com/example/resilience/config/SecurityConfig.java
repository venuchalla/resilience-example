package com.example.resilience.config;

import com.example.resilience.entities.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired JwtFilter jwtFilter;

  @Autowired private CustomAccessDeniedHandler accessDeniedHandler;
  @Autowired private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    // csrf.ignoringRequestMatchers("/h2-console/**","/signin","/","/signup" )
    // "/employees/**","/activity/**", "/products/**","/fraud/**"
    http.cors(c -> {})
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(
                        "/signin",
                        "/",
                        "/props/**",
                        "/signup",
                        "/swagger-ui/**",
                        "/api-docs/**",
                        "/swagger-resources/**",
                        "/actuator/**",
                        "/h2-console/**",
                        "/hello/**")
                    .permitAll()
                    .requestMatchers("/employees/**", "/fraud/**")
                    .hasAuthority(Role.ROLE_ADMIN.name())
                    .requestMatchers("/activity/**", "/products/**")
                    .hasAnyAuthority(Role.ROLE_USER.name(), Role.ROLE_ADMIN.name())
                    .anyRequest()
                    .authenticated())
        .exceptionHandling(
            ex ->
                // customAuthenticationEntryPoint,accessDeniedHandler
                ex.authenticationEntryPoint(customAuthenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    //  .httpBasic(withDefaults());
    return http.build();
  }

  // Global CORS configuration
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Angular app
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true); // If you use cookies/auth

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
