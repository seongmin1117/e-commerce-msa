package com.devsm.userservice.domain.auth.controller;

import com.devsm.userservice.domain.auth.dto.request.SignInRequestDto;
import com.devsm.userservice.domain.auth.dto.response.SignInResponse;
import com.devsm.userservice.domain.auth.service.AuthService;
import com.devsm.userservice.domain.auth.dto.request.SignUpRequestDto;
import com.devsm.commonserver.global.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("health-check")
    public String healthCheck(){
        return "auth-ok";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto<Long>> singUp(@RequestBody @Valid SignUpRequestDto dto){
        ResponseDto<Long> response = authService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<ResponseDto<SignInResponse>> signIn(@RequestBody @Valid SignInRequestDto dto){
        ResponseDto<SignInResponse> response = authService.signIn(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
