package notes_app.notesapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Configure URL authorization rules and disable API blockages
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF to allow POST, PUT, and DELETE requests from API tools like Postman
                .csrf(csrf -> csrf.disable())

                // Frame options must be disabled to allow the H2 console UI to load inside your browser frames
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to access the H2 database console link without checking credentials
                        .requestMatchers("/h2-console/**").permitAll()
                        // All other backend endpoints under /api/notes require an authenticated login
                        .anyRequest().authenticated()
                )
                // Enable HTTP Basic authentication (transmits credentials via standard headers)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 2. Define custom user credentials in memory
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Build a custom user profile
        UserDetails adminUser = User.builder()
                .username("atul")
                // "{bcrypt}" syntax tells Spring to expect an encrypted string
                .password(passwordEncoder.encode("atul123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(adminUser);
    }

    // 3. Define the encryption bean used to securely check passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}