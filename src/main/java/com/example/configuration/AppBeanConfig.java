package com.example.configuration;

import com.example.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppBeanConfig {

    private final UserRepository userRepository;
    //private final UserAuthenticationService userAuthenticationService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepository.findByUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

//    @Bean
//    public UserDetailsService userDetailsService(UserAuthenticationService userAuthenticationService) {
//        return userAuthenticationService::loadUserByUsername;
//    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp-relay.brevo.com");
        mailSender.setPort(587);
        mailSender.setUsername("pawfinder.team@gmail.com");
        mailSender.setPassword("ASK ALEX FOR API");

        return mailSender;
    }
}
