package com.devsm.userservice.domain.auth.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank @Email
    private String email;

    @NotBlank @Length(min = 8, max = 20)
    private String password;

    @NotBlank
    private String username;

    @NotBlank @Pattern(regexp = "^[0-9]{11,13}$")
    private String phoneNumber;

    @NotBlank
    private String address;
}
