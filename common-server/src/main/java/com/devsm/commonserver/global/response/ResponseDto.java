package com.devsm.commonserver.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {

    private String code;
    private String message;
    private T data;

    public static <T> ResponseDto<T> databaseError(){
        return new ResponseDto<>(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR, null);
    }

    public static <T> ResponseDto<T> success(){
        return new ResponseDto<>(ResponseCode.SUCCESS, ResponseMessage.SUCCESS,null);
    }
    public static <T> ResponseDto<T> success(T data){
        return new ResponseDto<>(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, data);
    }


}
