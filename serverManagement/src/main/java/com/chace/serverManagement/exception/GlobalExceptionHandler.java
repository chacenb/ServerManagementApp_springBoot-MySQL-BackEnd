package com.chace.serverManagement.exception;

import com.chace.serverManagement.Model.utils.ResponseStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override /* default handler for ALL EXCEPTIONS */
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    return ResponseEntity.badRequest().body(
        ResponseStructure.builder()
            .timeStamp(ZonedDateTime.now())
            .statusCode(statusCode.value())
            .status(HttpStatus.valueOf(statusCode.value()))
            .message(ex.getMessage())
            .error(ex)
            .build());
  }

  @ExceptionHandler({CustomException.class,}) /* handler for our custom exception */
  public ResponseEntity<Object> customExceptionHandler(CustomException ex) {
    log.error("[ERROR] customExceptionHandler >>> ", ex);
    return ResponseEntity.badRequest().body(
        ResponseStructure.builder()
            .timeStamp(ZonedDateTime.now())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .status(HttpStatus.BAD_REQUEST)
            .message(ex.getMessage())
            .build());
  }

  @Override /* handleMethodArgumentNotValid */
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    log.error("[ERROR] handleMethodArgumentNotValid >>> ", exception);

    /* get all the errors messages for clean returning */
    List<String> errorList = exception.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

    return ResponseEntity.badRequest().body(
        ResponseStructure.builder()
            .timeStamp(ZonedDateTime.now())
            .status(HttpStatus.valueOf(status.value()))
            .statusCode(status.value())
            .message(errorList.toString())
            .build()
    );
  }




  /* We can override many other methods coming from "ResponseEntityExceptionHandler" to define a custom bihavior if needed */

}

