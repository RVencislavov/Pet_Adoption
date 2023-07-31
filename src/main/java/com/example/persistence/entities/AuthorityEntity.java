package com.example.persistence.entities;

import com.example.persistence.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class AuthorityEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private long roleID;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private RoleEnum authority;

    @Override
    public String getAuthority() {
        return authority.name();
    }

    public void setAuthority(RoleEnum authority) {
        this.authority = authority;
    }

    public AuthorityEntity() {
    }

    @Override
    public String toString() {
        return authority != null ? authority.name() : "";
    }
}