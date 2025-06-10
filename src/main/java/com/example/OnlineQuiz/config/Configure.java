package com.example.OnlineQuiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.OnlineQuiz.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class Configure {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    // ✅ Define BCrypt encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Security filter chain
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // return http
    // .csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(request -> request
    // .requestMatchers("/api/users", "/api/users/login", "/sde").permitAll()
    // .anyRequest().authenticated()
    // )
    // .sessionManagement(session ->
    // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // )
    // .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    // .httpBasic(Customizer.withDefaults())
    // .build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        // .requestMatchers("/api/auth/**", "/api/quizzes",
                        // "/api/quizzes/**").permitAll()
                        // .requestMatchers("/api/quiz/attempt/**").hasRole("USER")
                        // .requestMatchers("/api/results/user/**").hasRole("USER")
                        // .requestMatchers("/api/results/admin/**").hasRole("ADMIN")
                        // .requestMatchers("/api/users/all", "/api/questions/**",
                        // "/api/options/**").hasRole("ADMIN")
                        // .anyRequest().authenticated())

                        .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                        .requestMatchers("/api/auth/profile").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    // // ✅ In-memory user store
    // @Bean
    // public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
    // UserDetails user1 = User
    // .builder()
    // .username("pavan")
    // .password(encoder.encode("ppkc"))
    // .roles("USER")
    // .build();

    // UserDetails user2 = User
    // .builder()
    // .username("user2")
    // .password(encoder.encode("pass2"))
    // .roles("ADMIN")
    // .build();

    // return new InMemoryUserDetailsManager(user1, user2);
    // }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
