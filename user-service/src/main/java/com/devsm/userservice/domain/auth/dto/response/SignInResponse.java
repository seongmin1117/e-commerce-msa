package com.devsm.userservice.domain.auth.dto.response;


import lombok.Getter;

@Getter
public class SignInResponse {
    private String token;
    private int expirationTime;

    public SignInResponse(String token) {
        this.token = token;
        this.expirationTime = 3600;
    }
}
