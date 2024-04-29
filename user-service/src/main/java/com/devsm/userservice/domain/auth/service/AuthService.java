package com.devsm.userservice.domain.auth.service;

import com.devsm.userservice.domain.auth.dto.request.SignInRequestDto;
import com.devsm.commonserver.global.jwt.JwtProvider;
import com.devsm.commonserver.global.response.ResponseDto;
import com.devsm.userservice.domain.auth.dto.request.SignUpRequestDto;
import com.devsm.userservice.domain.auth.dto.response.SignInResponse;
import com.devsm.userservice.domain.auth.exception.AuthException;
import com.devsm.userservice.domain.user.entity.Role;
import com.devsm.userservice.domain.user.entity.User;
import com.devsm.userservice.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;


    // 회원가입
    public ResponseDto<Long> signUp(SignUpRequestDto dto) {

        String email = dto.getEmail();
        if (userRepository.existsByEmail(email)) throw new AuthException.DuplicateEmailException();

        String phoneNumber = dto.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) throw new AuthException.DuplicatePhoneNumberException();

        String password = dto.getPassword();
        dto.setPassword(bCryptPasswordEncoder.encode(password));

        User user = userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .uuid(UUID.randomUUID().toString())
                .role(Role.ROLE_USER)
                .build());

        return ResponseDto.success(user.getId());
    }

    // 로그인
    public ResponseDto<SignInResponse> signIn(SignInRequestDto dto) {

        String token = null;

        String email = dto.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("이메일 또는 비밀번호가 다릅니다."));

        String password = dto.getPassword();
        String encodedPassword = user.getPassword();
        boolean isMatched = bCryptPasswordEncoder.matches(password, encodedPassword);
        if (!isMatched) throw new EntityNotFoundException("이메일 또는 비밀번호가 다릅니다.");

        String role = user.getRole().toString();
        String uuid = user.getUuid();
        token = jwtProvider.create(email,uuid, role, 360000L);

        return ResponseDto.success(new SignInResponse(token));
    }

}
