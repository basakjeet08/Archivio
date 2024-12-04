package dev.anirban.archivio.security;


import dev.anirban.archivio.constants.UrlConstants;
import dev.anirban.archivio.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;

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
                                .requestMatchers(HttpMethod.POST, UrlConstants.CREATE_BOOK).hasRole(UserRole.LIBRARIAN.name())
                                .requestMatchers(HttpMethod.PUT, UrlConstants.PUT_BOOK).hasRole(UserRole.LIBRARIAN.name())
                                .requestMatchers(HttpMethod.DELETE, UrlConstants.DELETE_BOOK_BY_ID).hasRole(UserRole.LIBRARIAN.name())
                                .requestMatchers(HttpMethod.GET, UrlConstants.FIND_BOOK_ISSUE_QUERY).hasRole(UserRole.LIBRARIAN.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}