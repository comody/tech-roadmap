package com.project.techroadmap.exception;

import com.project.techroadmap.constant.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private int code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus().value();
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }
}