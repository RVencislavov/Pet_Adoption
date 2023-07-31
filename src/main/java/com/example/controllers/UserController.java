package com.example.controllers;

import com.example.configuration.auth.AuthenticationResponse;
import com.example.persistence.binding.UserLoginBindingModel;
import com.example.persistence.binding.UserRegisterBindingModel;
import com.example.persistence.entities.AuthorityEntity;
import com.example.persistence.entities.UserEntity;
import com.example.persistence.repositories.AuthorityRepository;
import com.example.persistence.view.UserViewModel;
import com.example.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.example.util.Utility.getSiteURL;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthorityRepository authorityRepository;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRegisterBindingModel());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegisterBindingModel request, Model model) {
        AuthenticationResponse response = userService.register(request);
        model.addAttribute("user", response);
        return "redirect:/";
    }

    @GetMapping
    public String showUserList(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("users", new UserLoginBindingModel());
        return "login";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateUserForm(@PathVariable("id") long id, Model model){
        UserViewModel existingUser = userService.getUserById(id);
        model.addAttribute("user", existingUser);
        model.addAttribute("userId", id);
        List<AuthorityEntity> roles = authorityRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "user-edit";
    }
    @PostMapping("/{id}/roles")
    public String updateUserRoles(@PathVariable long id, @ModelAttribute("user") UserViewModel user) {
        userService.updateUserRoles(id, user.getAuthorities());
        return "redirect:/users";
    }


    @PostMapping("/refresh-token")
    @ResponseBody
    public AuthenticationResponse refreshToken(@RequestParam String refreshToken) throws IOException {
        return userService.refreshToken(refreshToken);
    }

    @PostMapping("/process_register")
    public String processRegister(UserEntity user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        userService.registerEmailSender(user, getSiteURL(request));
        return "register-success";
    }


    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verify-success";
        } else {
            return "register-fail";
        }
    }
}
