package com.devsm.commonserver.global.exception;

import com.devsm.commonserver.global.response.ResponseCode;
import com.devsm.commonserver.global.response.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String DEFAULT_ERROR_MESSAGE = "관리자에게 문의하세요. ";

    @ExceptionHandler(value = {
            RuntimeException.class
    })
    public ResponseEntity<ResponseDto> handleRuntimeException(final RuntimeException exception) {
        final String message = exception.getMessage();
        log.error(message);
        ResponseDto responseDto = new ResponseDto<>(ResponseCode.DATABASE_ERROR,message,null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }


//    @ExceptionHandler(value = {
//            MethodArgumentNotValidException.class
//    })
//    public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
//        final ObjectError errorTarget = exception.getAllErrors().get(0);
//        final String defaultErrorMessage = errorTarget.getDefaultMessage();
//        final String objectName = errorTarget.getObjectName();
//        log.warn("Request Field Error - Message: {}, Target : {}", defaultErrorMessage, objectName);
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body();
//    }
//
//    @ExceptionHandler(value = {
//            IllegalArgumentException.class,
//            AuthException.DuplicateEmailException.class
//    })
//    public ResponseEntity<ResponseDto> handleBadRequestException(final RuntimeException exception) {
//        final String errorMessage = exception.getMessage();
//        log.warn(errorMessage);
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body();
//    }

    @ExceptionHandler(value = {
            EntityNotFoundException.class
    })
    public ResponseEntity<ResponseDto> handleNotFoundException(final RuntimeException exception) {
        final String errorMessage = exception.getMessage();
        log.warn(errorMessage);
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED,errorMessage,null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(result);
    }
}
