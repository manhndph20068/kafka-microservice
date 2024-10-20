package com.manhnd.commonservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
@Getter
@Setter
public class ServiceResult<T> {
    private int code;
    private String message;
    private T data;

    public ServiceResult() {
        code = 0;
        message = "OK";
    }

    public ServiceResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ServiceResult(String message) {
        this.code = 0;
        this.message = message;
    }

    public static <T> ResponseEntity<ServiceResult<T>> okEntity(T body) {
        return ResponseEntity.ok(ok(body));
    }

    public static <T> ServiceResult<T> ok(T body) {
        ServiceResult<T> response = new ServiceResult<>();
        response.setData(body);
        return response;
    }


}
