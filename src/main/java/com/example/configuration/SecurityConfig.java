package com.example.configuration;

import com.example.configuration.auth.JWTAuthenticationFilter;
import com.example.persistence.repositories.LoginProviderRepository;
import com.example.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity

public class SecurityConfig {
    private final JWTAuthenticationFilter jwtAuthFilter;
    private final UserRepository userRepository;
    private final LoginProviderRepository loginProviderRepository;

    //old one
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/register", "/home", "/users/login").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(fl -> fl
                        .loginPage("/users/login")
                        .usernameParameter("userEmail")
                        .passwordParameter("userPassword")
                        .failureUrl("/users/login?error=true")
                        .permitAll()
                )
                .oauth2Login((oauth2Login) -> oauth2Login
                        .defaultSuccessUrl("/", true)
                        .userInfoEndpoint((userInfo) -> userInfo
                                .userAuthoritiesMapper(grantedAuthoritiesMapper())
                        )
                )
                .logout(l -> l
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .build();
    }

    private GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            authorities.forEach((authority) -> {
                GrantedAuthority mappedAuthority;
                if (authority instanceof OidcUserAuthority) {
                    OidcUserAuthority userAuthority = (OidcUserAuthority) authority;
                    mappedAuthority = new OidcUserAuthority(
                            "ROLE_USER", userAuthority.getIdToken(), userAuthority.getUserInfo());
                } else if (authority instanceof OAuth2UserAuthority) {
                    OAuth2UserAuthority userAuthority = (OAuth2UserAuthority) authority;
                    mappedAuthority = new OAuth2UserAuthority(
                            "ROLE_USER", userAuthority.getAttributes());
                } else {
                    mappedAuthority = authority;
                }
                mappedAuthorities.add(mappedAuthority);
            });
            return mappedAuthorities;
        };
    }
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
        logoutSuccessHandler.setUseReferer(true);
        return logoutSuccessHandler;
    }

}