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
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
    log.error("globalExceptionHandler :: Exception = {}, request = {}", ex, request);
    System.out.println(ex);
    return ResponseEntity.badRequest().body(
        ResponseStructure.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message("Error = " + ex.getMessage() + " | When trying to hit = " + request)
            .error(ex)
            .build());
  }


  /**
   * Customize the handling of {@link NoResourceFoundException}.
   * <p>This method delegates to {@link #handleExceptionInternal}.
   *
   * @param ex      the exception to handle
   * @param headers the headers to use for the response
   * @param status  the status code to use for the response
   * @param request the current request
   * @return a {@code ResponseEntity} for the response to use, possibly
   * {@code null} when the response is already committed
   * @since 6.1
   */
  @Override
  protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    return super.handleNoResourceFoundException(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    /* get all the errors messages for clean returning */
    List<String> errorList = exception.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

    log.error("Method Arguments are Not Valid :: errors = {}, status = {}", errorList, status);

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

