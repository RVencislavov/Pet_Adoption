package com.example.service.impl;

import com.example.persistence.entities.LoginProviderEntity;
import com.example.persistence.entities.UserEntity;
import com.example.persistence.enums.RoleEnum;
import com.example.persistence.repositories.GoogleAuthorityRepository;
import com.example.persistence.repositories.LoginProviderRepository;
import com.example.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoogleAuthorityRepository googleAuthorityRepository;

    @Autowired
    private LoginProviderRepository loginProviderRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        if(user.getAttribute("email") == null){
            throw new OAuth2AuthenticationException(new OAuth2Error("email_not_found",
                    "The 'email' attribute is not available in user attributes", ""));
        }

        String email = user.getAttribute("email");

        UserEntity newUser = userRepository.findByUserEmail(email)
                .orElseGet(() -> saveNewUser(userRequest, user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(newUser.getUserEmail())),
                user.getAttributes(),
                "email"
        );
    }


    private UserEntity saveNewUser(OAuth2UserRequest userRequest, OAuth2User user) {
        String firstName = user.getAttribute("given_name");
        String lastName = user.getAttribute("family_name");
        String photoURL = user.getAttribute("picture");
        String email = user.getAttribute("email");
        String accessToken = userRequest.getAccessToken().getTokenValue();
        UserEntity newUser = new UserEntity();
        newUser.setUserEmail(email);
        newUser.setUserFirstName(firstName);
        newUser.setUserLastName(lastName);
        newUser.setUserAccessToken(accessToken);
        newUser.setUserPhotoURL(photoURL);
        newUser.setEnabled(true);
        userRepository.save(newUser);
        newUser.setAuthorities(new HashSet<>(googleAuthorityRepository.findAllByAuthority(RoleEnum.USER)));
        userRepository.save(newUser);
        LoginProviderEntity newLoginProvider = new LoginProviderEntity();
        newLoginProvider.setUserId(newUser);
        newLoginProvider.setProviderName(userRequest.getClientRegistration().getRegistrationId());
        newLoginProvider.setAccessToken(accessToken);
        loginProviderRepository.save(newLoginProvider);

        return newUser;
    }

}
