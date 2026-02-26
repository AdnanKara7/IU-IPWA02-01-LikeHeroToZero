package de.iubh.likeherotozero;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                // 1. Öffentlich zugänglich: Startseite, Login und Ressourcen
                .requestMatchers("/", "/login", "/favicon.ico", "/error", "/css/**", "/js/**").permitAll()
                
                // 2. Admin-Exklusiv: Nur der Herausgeber darf Daten offiziell freigeben oder LÖSCHEN
                // Diese Zeile muss VOR der allgemeinen Backend-Regel stehen
                .requestMatchers("/freigeben/**", "/loeschen/**").hasRole("ADMIN")
                
                // 3. Backend-Zugriff: Sowohl Wissenschaftler als auch Admin dürfen hier arbeiten
                .requestMatchers("/backend/**", "/neu/**", "/bearbeiten/**", "/speichern/**", "/speichern").hasAnyRole("SCIENTIST", "ADMIN")
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/backend", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );
        
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Der Wissenschaftler (Experte): Erfasst und korrigiert Daten
        UserDetails scientist = User.withUsername("wissenschaftler")
                .password("{noop}co2pass") 
                .roles("SCIENTIST") 
                .build();

        // Der Admin (Herausgeber): Prüft, gibt Daten frei und löscht
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123") 
                .roles("ADMIN") 
                .build();

        return new InMemoryUserDetailsManager(scientist, admin);
    }
}