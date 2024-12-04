package dev.anirban.archivio.security;


import dev.anirban.archivio.constants.UrlConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(HttpMethod.POST, UrlConstants.REGISTER_LIBRARIAN).permitAll()
                                .requestMatchers(HttpMethod.POST, UrlConstants.LOGIN_LIBRARIAN).permitAll()
                                .requestMatchers(HttpMethod.POST, UrlConstants.REGISTER_MEMBER).permitAll()
                                .requestMatchers(HttpMethod.POST, UrlConstants.LOGIN_MEMBER).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}