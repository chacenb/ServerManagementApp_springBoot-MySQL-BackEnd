package com.chace.serverManagement.Model.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//@ControllerAdvice
@Slf4j
@RestControllerAdvice
public class FtaExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException notValidException, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    log.info("handleMethodArgumentNotValid :: notValidException = {},\n headers = {},\n status = {},\n request = {}", notValidException, headers, status, request);
    return ResponseEntity.badRequest().body(
        ResponseStructure.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .message(notValidException.getMessage())
            .data(null)
            .build()
    );
  }


//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException notValidException, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//    log.info("handleMethodArgumentNotValid :: notValidException = {}, headers = {}, status = {}, request = {}", notValidException, headers, status, request);
//
//    List<String> errorList = notValidException
//        .getBindingResult()
//        .getFieldErrors()
//        .stream()
//        .map(DefaultMessageSourceResolvable::getDefaultMessage)
//        .toList();
//
//    ResponseStructure res = ResponseStructure.builder()
//        .timeStamp(LocalDateTime.now())
//        .status(HttpStatus.BAD_REQUEST)
//        .statusCode(HttpStatus.BAD_REQUEST.value())
//        .message(errorList.toString())
//        .data(null)
//        .build();
//
//    return this.handleExceptionInternal(notValidException, res, headers, status, request);
////  {
////    "timeStamp": "2024-06-03T00:23:10.1974713",
////      "statusCode": 400,
////      "status": "BAD_REQUEST",
////      "message": "[datacenter name can't be empty or null]"
////  }
//  }


//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//    List<String> errorList = ex
//        .getBindingResult()
//        .getFieldErrors()
//        .stream()
//        .map(fieldError -> fieldError.getDefaultMessage())
//        .collect(Collectors.toList());
//    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorList);
//    return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
//  }
}

