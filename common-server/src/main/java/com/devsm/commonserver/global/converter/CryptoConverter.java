package com.devsm.commonserver.global.converter;

import com.devsm.commonserver.global.util.AesUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Converter
@RequiredArgsConstructor
@Component
public class CryptoConverter implements AttributeConverter<String,String>{

    private final AesUtil aesUtil;

    @Override
    public String convertToDatabaseColumn(String plainText) {
        if (plainText == null) return null;
        try {
            return aesUtil.aesEncrypt(plainText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String encryptedText) {
        if (encryptedText == null) return null;
        try {
            return aesUtil.aesDecrypt(encryptedText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
