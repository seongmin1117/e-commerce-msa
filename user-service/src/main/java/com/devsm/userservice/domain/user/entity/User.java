package com.devsm.userservice.domain.user.entity;

import com.devsm.commonserver.global.BaseEntity;
import com.devsm.commonserver.global.converter.CryptoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CryptoConverter.class)
    private String email;

    private String password;

    @Convert(converter = CryptoConverter.class)
    private String username;

    @Convert(converter = CryptoConverter.class)
    private String phoneNumber;

    @Convert(converter = CryptoConverter.class)
    private String address;

    @Convert(converter = CryptoConverter.class)
    private String uuid;

    @Enumerated(EnumType.STRING)
    private Role role;


}
