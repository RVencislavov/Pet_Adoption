package com.example.service;

import com.example.configuration.auth.AuthenticationResponse;
import com.example.exceptions.UserNotFoundException;
import com.example.persistence.binding.UserAddBindingModel;
import com.example.persistence.binding.UserLoginBindingModel;
import com.example.persistence.binding.UserRegisterBindingModel;
import com.example.persistence.entities.AuthorityEntity;
import com.example.persistence.entities.UserEntity;
import com.example.persistence.view.UserViewModel;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    AuthenticationResponse register(UserRegisterBindingModel request);

    AuthenticationResponse authenticate(UserLoginBindingModel request);

    void saveUserToken(UserEntity userEntityToken, String jwtToken, String refreshToken);

    void revokeAllUserTokens(UserEntity userEntity);

    AuthenticationResponse refreshToken(String refreshToken) throws IOException;

    UserDetails toUserDetails(UserEntity userEntity);

    UserDetails loadUserByUsername(String username);

    UserViewModel getUserById(long userId);

    void updateUser(long id, UserAddBindingModel userAddBindingModel);

    void updateUserRoles(long id, Set<AuthorityEntity> newAuthorities);
    List<UserEntity> getAllUsers();

    boolean emailExists(String email);

    void updateResetPasswordToken(String token, String email) throws UserNotFoundException;

    UserEntity getByResetPasswordToken(String token);

    void updatePassword(UserEntity user, String newPassword);

    void registerEmailSender(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void sendVerificationEmail(UserEntity user, String siteURL) throws MessagingException, UnsupportedEncodingException;

    boolean verify(String verificationCode);

}
