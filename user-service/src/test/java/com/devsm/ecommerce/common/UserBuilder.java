package com.devsm.ecommerce.common;

import com.devsm.userservice.domain.user.entity.User;
import com.devsm.userservice.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    @Autowired
    private UserRepository userRepository;

    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String address;

    public UserBuilder defaultUser(){
        this.email = "test@gmail.com";
        this.password = "test1234";
        this.username = "test";
        this.phoneNumber = "01012345678";
        this.address = "서울";

        return this;
    }

}
