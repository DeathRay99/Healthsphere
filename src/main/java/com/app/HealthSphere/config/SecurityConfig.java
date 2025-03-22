package com.app.HealthSphere.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF (for testing only)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // ✅ Allow all requests without authentication
                );

        return http.build();
    }
}

//package com.app.HealthSphere.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()  // Disable CSRF (for testing only; enable in production)
//                .authorizeHttpRequests(auth -> auth
//                        .antMatchers(HttpMethod.POST, "/api/consultants/**").hasRole("ADMIN")
//                        .antMatchers(HttpMethod.PUT, "/api/consultants/**").hasRole("ADMIN")
//                        .antMatchers(HttpMethod.DELETE, "/api/consultants/**").hasRole("ADMIN")
//                        .antMatchers(HttpMethod.GET, "/api/consultants/**").hasAnyRole("USER", "ADMIN")
//                        .anyRequest().authenticated()  // Default: require authentication for other requests
//                )
//                .httpBasic(); // Use HTTP Basic authentication (for simplicity in testing)
//
//        return http.build();
//    }
//}
