package com.busanit501.shoesproject.controller.kdkcontroller.kdkadvice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class kdkCustomRestAdvice {
    //게시글 작성할 때 유효성 체크
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
        log.error("BindException check : "+e);

        //전달할 에러 데이터
        Map<String, String> errorMap = new HashMap<>();
        if(e.hasErrors()){
            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }
        return ResponseEntity.badRequest().body(errorMap);
    }

    //없는 게시글에 댓글 작성 시 , 예외 처리
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleNoSuchElementException(Exception e) {
        log.error("handleNoSuchElementException check : "+e);

        //전달할 에러 데이터
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("time",""+System.currentTimeMillis());
        errorMap.put("msg","제약 조건 위반");

        return ResponseEntity.badRequest().body(errorMap);
    }


}
