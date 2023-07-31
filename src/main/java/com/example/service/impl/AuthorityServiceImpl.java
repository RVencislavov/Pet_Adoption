package com.example.service.impl;

import com.example.persistence.entities.AuthorityEntity;
import com.example.persistence.enums.RoleEnum;
import com.example.persistence.repositories.AuthorityRepository;
import com.example.service.AuthorityService;
import org.springframework.stereotype.Service;


@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void seedAuthorities() {
        AuthorityEntity admin = new AuthorityEntity();
        admin.setAuthority(RoleEnum.ADMIN);
        AuthorityEntity user = new AuthorityEntity();
        user.setAuthority(RoleEnum.USER);
        authorityRepository.save(admin);
        authorityRepository.save(user);
    }

}
