package com.example.restapi.config;

import com.example.restapi.jwt.JwtTokenFilter;
import com.example.restapi.jwt.JwtTokenProvider;
import com.example.restapi.model.Role;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity.authorizeHttpRequests(authManager -> authManager
                        .requestMatchers(HttpMethod.GET, "/success").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login", "/refresh", "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").hasRole(Role.RoleName.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, "/users/block").hasRole(Role.RoleName.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, "/users/roles").hasRole(Role.RoleName.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/users/questions").authenticated()
                        .requestMatchers(HttpMethod.POST, "/session-check").authenticated()
                        .requestMatchers(HttpMethod.GET, "/roles").hasRole(Role.RoleName.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/logs/**").hasRole(Role.RoleName.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/files").hasAnyRole(
                                Role.RoleName.ADMIN.name(),
                                Role.RoleName.USER.name(),
                                Role.RoleName.DEMO.name()
                        )
                        .requestMatchers(HttpMethod.PATCH, "/users/change-password").authenticated()
                        .anyRequest()
                        .authenticated()
        );
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Access-Control-Allow-Origin",
                "Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "Access-Control-Max-Age",
                "Access-Control-Request-Headers", "Access-Control-Request-Method"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
