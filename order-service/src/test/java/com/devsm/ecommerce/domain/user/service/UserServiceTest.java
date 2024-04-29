package com.devsm.ecommerce.domain.user.service;

import com.devsm.ecommerce.common.ServiceTest;
import com.devsm.ecommerce.common.UserBuilder;
import com.devsm.orderservice.domain.user.entity.User;
import com.devsm.orderservice.domain.user.repository.UserRepository;
import com.devsm.orderservice.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends ServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBuilder userBuilder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


}