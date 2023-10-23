package com.application.BookingApplication.config;

import com.application.BookingApplication.components.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyConfig {

    @Autowired
    private UserRepository userRepository;

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails userDetails = User.builder().
//                username("shubham")
//                .password(passwordEncoder().encode("123")).roles("ADMIN").
//                build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
