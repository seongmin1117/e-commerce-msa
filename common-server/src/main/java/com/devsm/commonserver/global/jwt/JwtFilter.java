package com.devsm.commonserver.global.jwt;

import com.devsm.commonserver.global.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = parseBearerToken(request);
        if (token == null) { // 검증 실패한 경우
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtProvider.isExpired(token)) { // 토큰이 만료된 경우
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtProvider.getEmail(token);
        if (email == null) { // 이메일이 없는 경우
            filterChain.doFilter(request, response);
            return;
        }
        String role = jwtProvider.getRole(token);
        if (role == null) { // 인가된게 없는 경우
            filterChain.doFilter(request, response);
            return;
        }
        String uuid = jwtProvider.getUuid(token);
        if (uuid == null) { // uuid가 없는 경우
            filterChain.doFilter(request, response);
            return;
        }
        // 검증이 완료되었으면
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role)); // 리스트에 roll 넣기
        UserDetailsImpl userDetails = new UserDetailsImpl(uuid,role);

        // 시큐리티 인증 토큰 생성
        AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 임시 컨텍스트 생성 후 등록
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);

        filterChain.doFilter(request, response);

    }

    // 요청값에서 헤더를 찾고 검증
    private String parseBearerToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        boolean hasAuthorization = authorization != null && authorization.startsWith("Bearer ");
        if (!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;
        return authorization.substring(7);

    }
}
