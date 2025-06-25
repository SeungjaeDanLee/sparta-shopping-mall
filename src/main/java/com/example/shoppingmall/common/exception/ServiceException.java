package com.example.shoppingmall.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceException extends RuntimeException {

    String code;
    String message;

    public ServiceException(ServiceExceptionCode response) {
        super(response.getMessage());
        this.code = response.name();
        this.message = super.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
} 