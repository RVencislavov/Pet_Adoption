package com.example.persistence.view;

public class AuthorityViewModel {
    private String authority;

    public AuthorityViewModel() {
    }

    public AuthorityViewModel(String authority) {

        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {

        this.authority = authority;
    }
}
