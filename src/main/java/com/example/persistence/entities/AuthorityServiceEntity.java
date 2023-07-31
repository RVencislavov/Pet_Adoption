package com.example.persistence.entities;

import com.example.persistence.enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorityServiceEntity {

    private RoleEnum authority;


}
