package com.devsm.commonserver.global.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AesUtil {
    @Value("${aes.secret}")
    private String SECRET_KEY; // AES 암호화에 사용할 키

    @Value("${aes.iv}")
    private String IV; // AES 암호화에 사용할 벡터 값

    public String aesEncrypt(String plainText) throws Exception {
        // 비밀 키 설정
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        // 초기화 할 벡터 설정
        IvParameterSpec IVSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

        // AES/CBC/PKCS5Padding 모드의 Cipher 객체 생성 및 초기화
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IVSpec);

        // 평문을 바이트 배열로 변환하여 암호화
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // 암호화 된 바이트 배열을 Base64로 인코딩하여 모든 문자를 아스키코드로 변환 후 반환
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String aesDecrypt(String encryptedText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec IVSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IVSpec);

        // Base64로 인코딩된 암호문을 바이트 배열로 디코딩
        byte[] decoded = Base64.getDecoder().decode(encryptedText);

        // 암호문을 복호화하여 평문으로 변환하여 반환
        return new String(cipher.doFinal(decoded), StandardCharsets.UTF_8);
    }
}
