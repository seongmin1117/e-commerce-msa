package com.devsm.userservice.domain.user.repository;

import com.devsm.userservice.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<com.devsm.userservice.domain.user.entity.User, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);


    Optional<User> findByEmail(String email);

}
