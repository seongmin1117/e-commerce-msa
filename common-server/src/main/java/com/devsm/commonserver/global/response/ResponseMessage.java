package com.devsm.commonserver.global.response;

public interface ResponseMessage {
    // HTTP Status 200
    String SUCCESS = "Success"; // 성공

    // HTTP Status 400
    String VALIDATION_FAILED = "Validation filed"; // 유효성 검증 실패
    String DUPLICATE_EMAIL = "Duplicate email"; // 중복된 이메일
    String DUPLICATE_PHONE_NUMBER = "Duplicate phone_number"; // 중복된 번호
    String NOT_EXISTED_USER = "This user does not exist"; // 존재하지 않는 유저
    String NOT_EXISTED_PRODUCT = "This product does not exist"; // 존재하지 않는 상품
    String NOT_EXISTED_ORDER = "This order does not exist"; // 존재하지 않는 주문
    String NOT_EXISTED = "존재하지 않습니다."; // 존재하지 않을 때

    // HTTP Status 401
    String SIGN_IN_FAILED = "Login information mismatch"; // 로그인 실패
    String AUTHORIZATION_FAILED = "Authorization failed"; // 인증 실패

    // HTTP Status 403
    String NO_PERMISSION = "Do not have permission"; // 권한 없음

    // HTTP Status 500
    String DATABASE_ERROR = "Database error"; // 데이터베이스 에러
}
