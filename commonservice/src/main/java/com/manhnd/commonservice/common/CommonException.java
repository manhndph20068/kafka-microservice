package com.manhnd.commonservice.common;

import lombok.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@Setter
@AllArgsConstructor
public class CommonException extends RuntimeException{
    private final int code;

    public CommonException(String message) {
        super(message);
        this.code = BAD_REQUEST.value();
    }

    public CommonException(String message, int code) {
        super(message);
        this.code = code;
    }

}
