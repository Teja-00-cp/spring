package com.example.hosptiAl.confif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Config {
//    @Autowired
//    private JwtAuthFilter authFilter;
//
//    @Bean
//    // authentication
//    public UserDetailsService userDetailsService() {
//        return new UserInfoUserDetailsService();
//    }

    // New bean to define the CORS configuration
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        // Allow requests from the specified origin
//        configuration.setAllowedOrigins(Collections.singletonList("http://127.0.0.1:5500"));
//        // Allow specific HTTP methods (e.g., POST, GET, PUT, DELETE)
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        // Allow all headers
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        // Allow credentials (like cookies and authentication headers)
//        configuration.setAllowCredentials(true);
//        // Set the maximum age of the CORS pre-flight request
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        // Apply this configuration to all paths
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable CSRF protection for API endpoints, as it's not needed for a public API
                .csrf(csrf -> csrf.disable())
                // Allow all requests to all endpoints
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                // Disable default login mechanisms to use your custom authentication logic
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .build();
    }
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF protection// Enable CORS and use the defined source
//                .cors(cors -> {}) 
//                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/forgot/**","/addUser","/pat/**", "/forall", "/authenticate","/getalldoc", "/addPatient", "/addDoc", "/appointment/**")
//                		
//                		.requestMatchers("/**").permitAll()
////                		.requestMatchers("/Home.html","/Login.html","/authenticate","http://127.0.0.1:5500/**","/user/**","/actuator/**").permitAll()
////                        .requestMatchers("/**").authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
}
