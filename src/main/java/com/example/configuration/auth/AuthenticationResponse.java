package com.example.configuration.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@RequiredArgsConstructor

public class AuthenticationResponse {

    private String token;

    @JsonProperty("access_token")
    private String userAccessToken;
    @JsonProperty("refresh_token")
    private String userRefreshToken;
}
