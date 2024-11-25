package com.biblioteca.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable()) // Desativa CSRF (opcional para APIs REST)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home", "/login", "/css/**", "/js/**").permitAll() // Páginas públicas
                .anyRequest().authenticated() // Requer autenticação para outras páginas
            )
            .httpBasic(Customizer.withDefaults()) // Ativa autenticação HTTP Basic
            .formLogin(form -> form
                .permitAll() // Permite acesso ao login padrão do Spring Security
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    // Configuração de usuários em memória
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER").build(),
            User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build()
        );
    }

    // Configuração do PasswordEncoder (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
