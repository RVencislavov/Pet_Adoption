package com.example.configuration.auth;

import com.example.service.impl.JWTServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTServiceImpl jwtServiceImpl;
    private final UserDetailsService userDetailsService;


    //if i use cookies (userAuthController/authenticate)
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String jwt;
        final String userEmail;
        Cookie[] cookies = request.getCookies();
        Cookie jwtCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    jwtCookie = cookie;
                    break;
                }
            }
        }
        if (jwtCookie == null) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = jwtCookie.getValue();
        userEmail = jwtServiceImpl.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtServiceImpl.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(@NotNull HttpServletRequest request,
//                                    @NotNull HttpServletResponse response,
//                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")){
//            filterChain.doFilter(request, response);
//            return;
//        }
//        jwt = authHeader.substring(7);
//        userEmail = jwtServiceImpl.extractUsername(jwt);
//
//        //checking if user is authenticated, if not we check database
//        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            //checking if the user is valid or not, if true we create authToken
//
//            if(jwtServiceImpl.isTokenValid(jwt, userDetails)){
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                //we enforce/extend our authToken with the details of our request
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                //update authToken
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }


}
