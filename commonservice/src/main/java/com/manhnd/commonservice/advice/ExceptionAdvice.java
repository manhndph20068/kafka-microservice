package com.manhnd.commonservice.advice;

import com.manhnd.commonservice.common.CommonException;
import com.manhnd.commonservice.common.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleCommonException(CommonException e) {
        log.error("error: {}", e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getCode(), e.getMessage()));
    }
}
