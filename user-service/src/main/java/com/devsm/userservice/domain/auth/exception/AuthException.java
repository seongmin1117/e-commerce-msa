package com.devsm.userservice.domain.auth.exception;

public class AuthException extends RuntimeException{

    public AuthException(final String message) {
        super(message);
    }

    public static class DuplicateEmailException extends AuthException {

        public DuplicateEmailException() {
            super("중복되는 이메일이 존재합니다.");
        }
    }
    public static class DuplicatePhoneNumberException extends AuthException {

        public DuplicatePhoneNumberException() {
            super("중복되는 휴대폰 번호가 존재합니다.");
        }
    }
}
