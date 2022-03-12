package com.project.techroadmap.exception;

import com.project.techroadmap.constant.ErrorCode;
import lombok.Getter;

@Getter
public class AccessDeniedException extends RuntimeException {
        private final ErrorCode errorCode;
        private String message;

        public AccessDeniedException(ErrorCode errorCode){
            super(errorCode.getMessage());
            this.errorCode = errorCode;
        }
}

