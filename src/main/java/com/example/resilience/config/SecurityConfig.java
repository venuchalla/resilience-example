package com.example.resilience.config;


import com.example.resilience.entities.Role;
import com.example.resilience.services.CustomUserDetailService;
import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        //csrf.ignoringRequestMatchers("/h2-console/**","/signin","/","/signup" )
        //"/employees/**","/activity/**", "/products/**","/fraud/**"
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers ->
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/signin",
                                    "/",
                                    "/signup",
                                    "/swagger-ui/**",
                                    "/api-docs/**",
                                    "/swagger-resources/**",
                                    "/actuator/**",
                                    "/h2-console/**",
                            "/hello/**"
                            ).permitAll()
                            .requestMatchers("/employees/**","/fraud/**").hasAuthority(Role.ROLE_ADMIN.name())
                            .requestMatchers("/activity/**", "/products/**").hasAnyAuthority(Role.ROLE_USER.name(),Role.ROLE_ADMIN.name())
                            .anyRequest().authenticated())
                .exceptionHandling(ex ->
                       ex.authenticationEntryPoint(customAuthenticationEntryPoint)
                               .accessDeniedHandler(accessDeniedHandler))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
              //  .httpBasic(withDefaults());
        return http.build();
    }



}
