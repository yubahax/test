package com.example.yubatest.handler;


import cn.dev33.satoken.exception.NotLoginException;
import cn.soboys.restapispringbootstarter.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NestedServletException.class)
    public ResponseEntity<Result> handleNestedServletException(NestedServletException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.buildFailure("500","不存在token"));
    }
    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Result> handleNotLoginException(NotLoginException ex) {
        String errorMsg = ex.getMessage();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.buildFailure("500",errorMsg));
    }
}