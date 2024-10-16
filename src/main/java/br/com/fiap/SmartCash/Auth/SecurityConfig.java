package br.com.fiap.SmartCash.Auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http, AuthorizationFilter authorizationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(HttpMethod.GET, "/empresa").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/usuario/novaSenha").permitAll()
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/usuario").permitAll()
                        .requestMatchers(HttpMethod.GET,"/registroAssinatura/token").permitAll()
                        .requestMatchers(HttpMethod.POST,"/empresa").permitAll()
                        .requestMatchers(HttpMethod.POST,"/registroAssinatura").permitAll()
                        .requestMatchers(HttpMethod.GET,"/assinatura").permitAll()
                        .requestMatchers(HttpMethod.GET,"/assinatura/{id}").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll() 
                        .requestMatchers("/docs").permitAll()
                        .anyRequest().permitAll()
        );
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}