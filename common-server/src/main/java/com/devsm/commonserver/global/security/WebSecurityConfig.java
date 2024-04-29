package com.devsm.commonserver.global.security;

import com.devsm.commonserver.global.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { //암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println(httpSecurity);
        httpSecurity
                //cors 허용
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                //csrf disable
                .csrf(CsrfConfigurer::disable)

                //From 로그인 방식 disable
                .formLogin(AbstractHttpConfigurer::disable)

                //http basic 인증 방식 disable
                .httpBasic(HttpBasicConfigurer::disable)

                //세션 설정
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 상태 유지 X
                //경로별 인가 작업
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/auth/**", "/","/api/products/**").permitAll() // 회원가입,로그인은 모두
                        .requestMatchers("/api/users/**","/api/orders/**").hasRole("USER") // 유저 페이지는 유저만
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // 나중에 생길 관리자를 위해 ,,
                        .anyRequest().authenticated()) // 나머지 모든 요청은 인증된 사용자
                // 필터 등록
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsconfiguration = new CorsConfiguration();
        corsconfiguration.addAllowedOrigin("*"); //모든 오리진에 허용
        corsconfiguration.addAllowedMethod("*"); //모든 메소드에 허용
        corsconfiguration.addAllowedHeader("*"); //모든 헤더 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsconfiguration); // 모든 패턴에 대헤 적용

        return source;
    }
}

